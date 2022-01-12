package com.br.lead.desafioLEAD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lead.desafioLEAD.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	Categoria findByNome(String nome);	
}
