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

import com.br.lead.desafioLEAD.controller.dto.EstudioDto;
import com.br.lead.desafioLEAD.controller.form.EstudioForm;
import com.br.lead.desafioLEAD.controller.form.UpdateEstudioForm;
import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.repository.EstudioRepository;

@RestController
@RequestMapping("/api/estudios")
public class EstudiosController {
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	
	@GetMapping
	public List<EstudioDto> lista(){
			List<Estudio> estudios = estudioRepository.findAll();
			return EstudioDto.converter(estudios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstudioDto> detalhar(@PathVariable Integer id){
			Optional<Estudio> estudio = estudioRepository.findById(id);
			if (estudio.isPresent()) {
				return ResponseEntity.ok(new EstudioDto(estudio.get()));
			}
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EstudioDto> cadastrar(@RequestBody @Valid EstudioForm form, UriComponentsBuilder uriBuilder) {
		Estudio estudio = form.converter(estudioRepository);
		estudioRepository.save(estudio);
		
		URI uri = uriBuilder.path("/estudios/{id}").buildAndExpand(estudio.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstudioDto(estudio));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstudioDto> atualizar(@PathVariable Integer id, @RequestBody @Valid UpdateEstudioForm form){
		Optional<Estudio> optional = estudioRepository.findById(id);
		if (optional.isPresent()) {
			Estudio estudio = form.atualizar(id, estudioRepository);
			return ResponseEntity.ok(new EstudioDto(estudio));
		}
		
		return ResponseEntity.notFound().build();
	}
		
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id){
		Optional<Estudio> optional = estudioRepository.findById(id);
		if (optional.isPresent()) {
			estudioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
}


