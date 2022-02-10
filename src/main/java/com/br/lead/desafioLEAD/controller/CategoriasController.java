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

import com.br.lead.desafioLEAD.controller.dto.CategoriaDto;
import com.br.lead.desafioLEAD.controller.form.CategoriaForm;
import com.br.lead.desafioLEAD.controller.form.UpdateCategoriaForm;
import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	@GetMapping
	public List<CategoriaDto> lista(){
			List<Categoria> categorias = categoriaRepository.findAll();
			return CategoriaDto.converter(categorias);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> detalhar(@PathVariable Integer id){
			Optional<Categoria> categoria = categoriaRepository.findById(id);
			if (categoria.isPresent()) {
				return ResponseEntity.ok(new CategoriaDto(categoria.get()));
			}
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDto> cadastrar(@RequestBody @Valid CategoriaForm form, UriComponentsBuilder uriBuilder) {
		Categoria categoria = form.converter(categoriaRepository);
		categoriaRepository.save(categoria);
		
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDto(categoria));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<CategoriaDto> atualizar(@PathVariable Integer id, @RequestBody @Valid UpdateCategoriaForm form){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			Categoria categoria = form.atualizar(id, categoriaRepository);
			return ResponseEntity.ok(new CategoriaDto(categoria));
		}
		
		return ResponseEntity.notFound().build();
	}
		
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id){
		Optional<Categoria> optional = categoriaRepository.findById(id);
		if (optional.isPresent()) {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
}
