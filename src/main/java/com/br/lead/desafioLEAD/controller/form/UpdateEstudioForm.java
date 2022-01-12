package com.br.lead.desafioLEAD.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.repository.EstudioRepository;

public class UpdateEstudioForm {
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

	public Estudio atualizar(Integer id, EstudioRepository estudioRepository) {
		Estudio estudio = estudioRepository.getOne(id);
		
		estudio.setNome(this.nome);
		estudio.setDescricao(this.descricao);
		return estudio;
	}
}
