package com.br.lead.desafioLEAD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.lead.desafioLEAD.model.Estudio;

public interface EstudioRepository extends JpaRepository<Estudio, Integer>{
	Estudio findByNome(String nome);
}
