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
@RequestMapping("/filmes")
public class FilmesController {
	
	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	@GetMapping
	public List<FilmeDto> lista(String nomeCategoria){
			List<Filme> filmes = filmeRepository.findAll();
			return FilmeDto.converter(filmes);
		
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