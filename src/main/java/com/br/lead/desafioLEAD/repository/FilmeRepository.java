package com.br.lead.desafioLEAD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lead.desafioLEAD.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Integer>{	
	
	List<Filme> findByTituloContainingIgnoreCase(String titulo);
	
	List<Filme> findByTituloContainingIgnoreCaseAndCategoria_idAndEstudio_id(String titulo, int categoria_id, int estudio_id);
	
	List<Filme> findByTituloContainingIgnoreCaseAndCategoria_id(String titulo, int categoria_id);
	
	List<Filme> findByTituloContainingIgnoreCaseAndEstudio_id(String titulo, int estudio_id);
	
	List<Filme> findByCategoria_idAndEstudio_id(int categoria_id, int estudio_id);
	
	List<Filme> findByCategoria_id(int categoria_id);
	
	List<Filme> findByEstudio_id(int estudio_id);

	  
}
