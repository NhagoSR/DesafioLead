package com.br.lead.desafioLEAD.config.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.lead.desafioLEAD.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {	

	private String senhaToken = "123456";
	
	public String gerarToken(Authentication authentication) {
		Usuario logado = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + 1000 * 60 * 60 * 24);
		return Jwts.builder()
				.setIssuer("API do desafio Lead")
				.setSubject(Integer.toString(logado.getId()))
				.setIssuedAt(hoje)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, senhaToken)
				.compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser()
			.setSigningKey(this.senhaToken)
			.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getIdUsuario(String token) {
		Claims claims = Jwts.parser()
		.setSigningKey(this.senhaToken)
		.parseClaimsJws(token)
		.getBody();
		return Integer.parseInt(claims.getSubject());
	}

}
