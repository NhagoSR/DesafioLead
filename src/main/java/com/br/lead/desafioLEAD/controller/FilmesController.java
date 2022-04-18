package com.br.lead.desafioLEAD.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.lead.desafioLEAD.controller.dto.FilmeDto;
import com.br.lead.desafioLEAD.controller.form.FilmeForm;
import com.br.lead.desafioLEAD.controller.form.UpdateFilmeForm;
import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.model.Filme;
import com.br.lead.desafioLEAD.repository.CategoriaRepository;
import com.br.lead.desafioLEAD.repository.EstudioRepository;
import com.br.lead.desafioLEAD.repository.FilmeRepository;

@RestController
@RequestMapping("/api/filmes")
public class FilmesController {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	@GetMapping
	public List<FilmeDto> lista(){
			List<Filme> filmes = filmeRepository.findAll();
			return FilmeDto.converter(filmes);
		
	}
	
	@GetMapping("/filterTitulo")
	public List<FilmeDto> filmeByTitulo(@RequestParam("titulo") String titulo){
		List<Filme> filme = filmeRepository.findByTituloContainingIgnoreCase(titulo);
		return FilmeDto.converter(filme);
	}
	
	@GetMapping("/filterCategoria") 
	public List<FilmeDto> filmeByCategoriaId(@RequestParam(required = false, name = "categoria", defaultValue = "0") int categoria_id) { 
		if(categoria_id != 0) {
			List<Filme> filme = filmeRepository.findByCategoria_id(categoria_id); 
			return FilmeDto.converter(filme); 
		}
		return lista();
	}
	
	@GetMapping("/filterEstudio") 
	public List<FilmeDto> filmeByEstudioId(@RequestParam(required = false, name = "estudio", defaultValue = "0") int estudio_id) {
		if(estudio_id != 0) {
			List<Filme> filme = filmeRepository.findByEstudio_id(estudio_id);
			return FilmeDto.converter(filme);
		}
		return lista();
	}
	
	@GetMapping("/filterCategoriaEstudio") 
	public List<FilmeDto> filmeByCategoriaIdEstudioId(@RequestParam(required = false, name = "categoria") int categoria_id, 
			@RequestParam(required = false, name = "estudio", defaultValue = "0") int estudio_id) {
		List<Filme> filme;
		
		if(categoria_id != 0 && estudio_id != 0) {
			filme = filmeRepository.findByCategoria_idAndEstudio_id(categoria_id, estudio_id);
			return FilmeDto.converter(filme);
			
		} else if(categoria_id == 0 && estudio_id != 0) {
			filme = filmeRepository.findByEstudio_id(estudio_id);
			return FilmeDto.converter(filme);
			
		} else if(categoria_id != 0 && estudio_id == 0) {
			filme = filmeRepository.findByCategoria_id(categoria_id);
			return FilmeDto.converter(filme);
		}
		
		return lista();
	}
	
	@GetMapping("/filterTituloCategoriaEstudio") 
	public List<FilmeDto> filmeByTituloCategoriaIdEstudioId(
			@RequestParam(required = false, name = "titulo") String titulo, 
			@RequestParam(required = false, name = "categoria", defaultValue = "0") int categoria_id,
			@RequestParam(required = false, name = "estudio", defaultValue = "0") int estudio_id) { 
		List<Filme> filme;
		
		if(titulo.length() > 0 && categoria_id != 0 && estudio_id != 0) {
			filme = filmeRepository.findByTituloContainingIgnoreCaseAndCategoria_idAndEstudio_id(titulo, categoria_id, estudio_id); 
			return FilmeDto.converter(filme); 
			
		} else if (titulo.length() > 0 && categoria_id == 0 && estudio_id == 0) {
			filme = filmeRepository.findByTituloContainingIgnoreCase(titulo);
			return FilmeDto.converter(filme);
			
		} else if (titulo.length() > 0 && estudio_id != 0) {
			filme = filmeRepository.findByTituloContainingIgnoreCaseAndEstudio_id(titulo, estudio_id);
			return FilmeDto.converter(filme); 
		
		} else if (titulo.length() > 0 && categoria_id != 0) {
			filme = filmeRepository.findByTituloContainingIgnoreCaseAndCategoria_id(titulo, categoria_id);
			return FilmeDto.converter(filme);
			
		} else if(estudio_id == 0 && categoria_id != 0) {
			filme = filmeRepository.findByCategoria_id(categoria_id);
			return FilmeDto.converter(filme); 
			
		} else if(categoria_id == 0 && estudio_id != 0) {
			filme = filmeRepository.findByEstudio_id(estudio_id);
			return FilmeDto.converter(filme); 
			
		} else if (categoria_id != 0 && estudio_id != 0) {
			filme = filmeRepository.findByCategoria_idAndEstudio_id(categoria_id, estudio_id);
			return FilmeDto.converter(filme);
			
		} else if(categoria_id == 0 && estudio_id == 0) {
			return lista();
		}
		
		return lista();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FilmeDto> detalhar(@PathVariable Integer id){
			Optional<Filme> filme = filmeRepository.findById(id);
			if (filme.isPresent()) {
				return ResponseEntity.ok(new FilmeDto(filme.get()));
			}
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<FilmeDto> cadastrar(@RequestBody @Valid FilmeForm form, UriComponentsBuilder uriBuilder) {
		Optional<Estudio> estudioOptional = estudioRepository.findById(form.getIdEstudio());
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(form.getIdCategoria());
		
		if (estudioOptional.isPresent() && categoriaOptional.isPresent()) {
			Filme filme = form.converter(categoriaRepository, estudioRepository);
			filmeRepository.save(filme);
			
			URI uri = uriBuilder.path("/filme/{id}").buildAndExpand(filme.getId()).toUri();
			return ResponseEntity.created(uri).body(new FilmeDto(filme));
			
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FilmeDto> atualizar(@PathVariable Integer id, @RequestBody @Valid UpdateFilmeForm form){
		Optional<Filme> optional = filmeRepository.findById(id);
		Optional<Estudio> estudioOptional = estudioRepository.findById(form.getIdEstudio());
		Optional<Categoria> categoriaOptional = categoriaRepository.findById(form.getIdCategoria());
		
		if (optional.isPresent() && estudioOptional.isPresent() && categoriaOptional.isPresent()) {
			Filme filme = form.atualizar(id, filmeRepository, categoriaRepository, estudioRepository);
			return ResponseEntity.ok(new FilmeDto(filme));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id){
		Optional<Filme> optional = filmeRepository.findById(id);
		if (optional.isPresent()) {
			filmeRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}