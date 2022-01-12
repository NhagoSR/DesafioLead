package com.br.lead.desafioLEAD.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.br.lead.desafioLEAD.model.Categoria;
import com.br.lead.desafioLEAD.model.Estudio;
import com.br.lead.desafioLEAD.model.Filme;
import com.br.lead.desafioLEAD.repository.CategoriaRepository;
import com.br.lead.desafioLEAD.repository.EstudioRepository;
import com.br.lead.desafioLEAD.repository.FilmeRepository;

public class UpdateFilmeForm {
	@NotNull @NotEmpty
	private String titulo;
	
	@NotNull @NotEmpty
	private String sinopse;
	
	@NotNull 
	private double duracao;
	
	@NotNull @NotEmpty
	private String nomeDiretor;
	
	@NotNull 
	private Integer idCategoria;
	
	@NotNull 
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

	public Filme atualizar(Integer id, FilmeRepository filmeRepository, CategoriaRepository categoriaRepository, EstudioRepository estudioRepository) {
		Filme filme = filmeRepository.getOne(id);
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		Estudio estudio = estudioRepository.findById(idEstudio).get();
		
		filme.setTitulo(this.titulo);
		filme.setSinopse(this.sinopse);
		filme.setDuracao(this.duracao);
		filme.setNomeDiretor(this.nomeDiretor);
		filme.setCategoria(categoria);
		filme.setEstudio(estudio);
		return filme;
	}
	
	
}
