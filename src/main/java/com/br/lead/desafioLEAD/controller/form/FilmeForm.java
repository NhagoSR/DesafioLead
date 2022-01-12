package com.br.lead.desafioLEAD.controller.form;

import java.time.LocalDate;

import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.model.Filme;
import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.repository.CategoriaRepository;
import com.br.lead.desafioLEAD.repository.EstudioRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;


public class FilmeForm {
	
	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String sinopse;
	
	@NotNull 
	private LocalDate dataLancamento;
	
	@NotNull 
	private double duracao;
	
	@NotNull @NotEmpty
	private String nomeDiretor;
	
	@NotNull @NotFound
	private Integer idCategoria;
	
	@NotNull @NotFound
	private Integer idEstudio;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public LocalDate getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdEstudio() {
		return idEstudio;
	}

	public void setIdEstudio(Integer idEstudio) {
		this.idEstudio = idEstudio;
	}

	public Filme converter(CategoriaRepository categoriaRepository, EstudioRepository estudioRepository) {
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		Estudio estudio = estudioRepository.findById(idEstudio).get();
		return new Filme(titulo, sinopse, dataLancamento, duracao, nomeDiretor, categoria, estudio);
	}
	
	
	
	
}
