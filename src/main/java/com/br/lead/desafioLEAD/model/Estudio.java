package com.br.lead.desafioLEAD.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estudios")
public class Estudio {
	@Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "descricao")
	private String descricao;
	
	public Estudio() {
		
	}
	public Estudio(int id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public Estudio(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(descricao, id, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudio other = (Estudio) obj;
		return Objects.equals(descricao, other.descricao) && id == other.id && Objects.equals(nome, other.nome);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	
}
