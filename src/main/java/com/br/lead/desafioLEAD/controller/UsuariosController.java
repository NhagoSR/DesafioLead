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

import com.br.lead.desafioLEAD.controller.dto.UsuarioDto;
import com.br.lead.desafioLEAD.controller.form.UsuarioForm;
import com.br.lead.desafioLEAD.controller.form.UpdateUsuarioForm;
import com.br.lead.desafioLEAD.model.Usuario;
import com.br.lead.desafioLEAD.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping
	public List<UsuarioDto> lista(){
			List<Usuario> usuarios = usuarioRepository.findAll();
			return UsuarioDto.converter(usuarios);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalhar(@PathVariable Integer id){
			Optional<Usuario> usuario = usuarioRepository.findById(id);
			if (usuario.isPresent()) {
				return ResponseEntity.ok(new UsuarioDto(usuario.get()));
			}
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {		
		Optional<Usuario> emailUsuario = usuarioRepository.findByEmail(form.getEmail());
		
		if (!emailUsuario.isPresent()) {
			Usuario usuario = form.converter(usuarioRepository);
			usuarioRepository.save(usuario);
			
			URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
		} 
		return ResponseEntity.badRequest().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable Integer id, @RequestBody @Valid UpdateUsuarioForm form){
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			Usuario usuario = form.atualizar(id, usuarioRepository);
			return ResponseEntity.ok(new UsuarioDto(usuario));
		}
		
		return ResponseEntity.notFound().build();
	}
		
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Integer id){
		Optional<Usuario> optional = usuarioRepository.findById(id);
		if (optional.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}
}
