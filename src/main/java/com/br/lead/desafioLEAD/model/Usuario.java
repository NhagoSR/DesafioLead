package com.br.lead.desafioLEAD.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "senha")
	private String senha;
	@Column(name = "idade")
	private int idade;
	@Column(name = "email")
	private String email;
	@Column(name = "data_nascimento")
	private LocalDate data_nascimento;
	@Column(name = "privilegios_adm")
	private Boolean privilegios_adm;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nome, String senha, int idade, String email, LocalDate data_nascimento,
			Boolean privilegios_adm) {
		super();
		this.id = id;
		this.nome = nome;
		this.senha = senha;
		this.idade = idade;
		this.email = email;
		this.data_nascimento = data_nascimento;
		this.privilegios_adm = privilegios_adm;
	}
	
	public Usuario(String nome, String senha, int idade,String email, LocalDate data_nascimento,
			Boolean privilegios_adm) {
		super();
		this.nome = nome;
		this.senha = senha;
		this.idade = idade;
		this.email = email;
		this.data_nascimento = data_nascimento;
		this.privilegios_adm = privilegios_adm;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(data_nascimento, email, id, idade, nome, privilegios_adm, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(data_nascimento, other.data_nascimento) && Objects.equals(email, other.email)
				&& id == other.id && idade == other.idade && Objects.equals(nome, other.nome)
				&& Objects.equals(privilegios_adm, other.privilegios_adm) && Objects.equals(senha, other.senha);
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public Boolean getPrivilegios_adm() {
		return privilegios_adm;
	}
	public void setPrivilegios_adm(Boolean privilegios_adm) {
		this.privilegios_adm = privilegios_adm;
	}
	
	
	
}
