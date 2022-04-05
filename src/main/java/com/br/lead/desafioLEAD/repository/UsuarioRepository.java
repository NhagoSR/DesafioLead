package com.br.lead.desafioLEAD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lead.desafioLEAD.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	Optional<Usuario> findByEmail(String emailUsuario); 
}
