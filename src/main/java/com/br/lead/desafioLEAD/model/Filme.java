package com.br.lead.desafioLEAD.model;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="filmes")
public class Filme {
	@Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("_id")
	private int id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "sinopse")
	private String sinopse;
	@Column(name = "dataLancamento")
	private LocalDate dataLancamento;
	@Column(name = "duracao")
	private double duracao;
	@Column(name = "nomeDiretor")
	private String nomeDiretor;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "estudio_id")
	private Estudio estudio;
	
	public Filme() {
		
	}
	
	

	public Filme(String titulo, String sinopse, LocalDate dataLancamento, double duracao, String nomeDiretor,
			Categoria categoria, Estudio estudio) {
		super();
		this.titulo = titulo;
		this.sinopse = sinopse;
		this.dataLancamento = dataLancamento;
		this.duracao = duracao;
		this.nomeDiretor = nomeDiretor;
		this.categoria = categoria;
		this.estudio = estudio;
	}



	public Filme(int id, String titulo, String sinopse, LocalDate dataLancamento, double duracao, String nomeDiretor,
			Categoria categoria, Estudio estudio) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.sinopse = sinopse;
		this.dataLancamento = dataLancamento;
		this.duracao = duracao;
		this.nomeDiretor = nomeDiretor;
		this.categoria = categoria;
		this.estudio = estudio;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, dataLancamento, duracao, estudio, id, nomeDiretor, sinopse, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(dataLancamento, other.dataLancamento)
				&& Double.doubleToLongBits(duracao) == Double.doubleToLongBits(other.duracao)
				&& Objects.equals(estudio, other.estudio) && id == other.id
				&& Objects.equals(nomeDiretor, other.nomeDiretor) && Objects.equals(sinopse, other.sinopse)
				&& Objects.equals(titulo, other.titulo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Estudio getEstudio() {
		return estudio;
	}

	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	
}
