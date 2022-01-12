package com.br.lead.desafioLEAD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lead.desafioLEAD.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Integer>{

	List<Filme> findByCategoria_Nome(String nomeCategoria);
	  
}
