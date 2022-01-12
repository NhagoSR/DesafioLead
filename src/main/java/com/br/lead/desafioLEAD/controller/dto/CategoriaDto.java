package com.br.lead.desafioLEAD.controller.dto;
import java.util.List;
import java.util.stream.Collectors;

import com.br.lead.desafioLEAD.model.Categoria;

public class CategoriaDto {
	
	private int id;
	private String nome;


	public CategoriaDto(Categoria categoria) {
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
	public static List<CategoriaDto> converter(List<Categoria> categorias) {
		return categorias.stream().map(CategoriaDto::new).collect(Collectors.toList());
	}
}
