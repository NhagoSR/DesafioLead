package com.br.lead.desafioLEAD.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.lead.desafioLEAD.config.security.TokenService;
import com.br.lead.desafioLEAD.controller.dto.ErroDto;
import com.br.lead.desafioLEAD.controller.dto.TokenDto;
import com.br.lead.desafioLEAD.controller.form.LoginForm;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity autenticar(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));			
		} catch (AuthenticationException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("Email ou senha Invalidos");
		}
		
	}
}
