package com.br.lead.desafioLEAD.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.br.lead.desafioLEAD.model.Estudio;

public class EstudioDto {
	private int id;
	private String nome;
	private String descricao;


	public EstudioDto(Estudio estudio) {
		this.id = estudio.getId();
		this.nome = estudio.getNome();
		this.descricao = estudio.getDescricao();
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static List<EstudioDto> converter(List<Estudio> estudios) {
		return estudios.stream().map(EstudioDto::new).collect(Collectors.toList());
	}
}
