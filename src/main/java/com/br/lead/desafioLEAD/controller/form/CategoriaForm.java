package com.br.lead.desafioLEAD.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.repository.CategoriaRepository;


public class CategoriaForm {
	
	@NotNull @NotEmpty
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Categoria converter(CategoriaRepository categoriaRepository) {
		
		return new Categoria(nome);
	}
	
	
}
