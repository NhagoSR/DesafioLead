package com.br.lead.desafioLEAD.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.repository.CategoriaRepository;

public class UpdateCategoriaForm {
	@NotNull @NotEmpty
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria atualizar(Integer id, CategoriaRepository categoriaRepository) {
		Categoria categoria = categoriaRepository.getOne(id);
		
		categoria.setNome(this.nome);
		return categoria;
	}
}
