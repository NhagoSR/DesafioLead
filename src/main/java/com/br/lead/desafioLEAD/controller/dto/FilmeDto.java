package com.br.lead.desafioLEAD.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.model.Filme;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FilmeDto {
	@JsonProperty("_id")
	private int id;
	private String titulo;
	private String sinopse;
	private LocalDate dataLancamento;
	private String nomeDiretor;
	private double duracao;
	private Categoria categoria;
	private Estudio estudio;
	
	
	public FilmeDto(Filme filme) {
		this.id = filme.getId();
		this.titulo = filme.getTitulo();
		this.sinopse = filme.getSinopse();
		this.dataLancamento = filme.getDataLancamento();
		this.nomeDiretor = filme.getNomeDiretor();
		this.duracao = filme.getDuracao();
		this.categoria = filme.getCategoria();
		this.estudio = filme.getEstudio();
	}

	public int getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public String getSinopse() {
		return sinopse;
	}
	public LocalDate getDataLancamento() {
		return dataLancamento;
	}
	public String getNomeDiretor() {
		return nomeDiretor;
	}
	public double getDuracao() {
		return duracao;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public Estudio getEstudio() {
		return estudio;
	}

	public static List<FilmeDto> converter(List<Filme> filmes) {
		return filmes.stream().map(FilmeDto::new).collect(Collectors.toList());
	}
	
	
}
