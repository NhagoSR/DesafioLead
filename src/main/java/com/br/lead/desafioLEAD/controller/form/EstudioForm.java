package com.br.lead.desafioLEAD.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.repository.EstudioRepository;

public class EstudioForm {		
	@NotNull @NotEmpty
	private String nome;
	@NotNull
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Estudio converter(EstudioRepository estudioRepository) {
		
		return new Estudio(nome, descricao);
	}
}
