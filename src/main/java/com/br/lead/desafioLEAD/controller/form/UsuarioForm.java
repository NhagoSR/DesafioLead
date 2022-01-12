package com.br.lead.desafioLEAD.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.lead.desafioLEAD.model.Usuario;
import com.br.lead.desafioLEAD.repository.UsuarioRepository;

public class UsuarioForm {
	
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty @Length(min = 8)
	private String senha;
	@NotNull 
	private int idade;
	@NotNull @NotEmpty
	private String email;
	@NotNull 
	private LocalDate data_nascimento;
	@NotNull 
	private Boolean privilegios_adm;
	
	
	
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

	public Usuario converter(UsuarioRepository usuarioRepository) {
		
		return new Usuario(nome, senha, idade, email, data_nascimento, privilegios_adm);
	}
}
