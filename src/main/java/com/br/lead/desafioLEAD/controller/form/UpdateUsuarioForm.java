package com.br.lead.desafioLEAD.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.br.lead.desafioLEAD.model.Usuario;
import com.br.lead.desafioLEAD.repository.UsuarioRepository;

public class UpdateUsuarioForm {
	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty @Length(min = 8)
	private String senha;
	@NotNull 
	private int idade;
	@NotNull
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
	
	public Usuario atualizar(Integer id, UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(id);
		
		usuario.setNome(nome);
		usuario.setSenha(senha);
		usuario.setIdade(idade);
		usuario.setEmail(email);
		usuario.setData_nascimento(data_nascimento);
		usuario.setPrivilegios_adm(privilegios_adm);
		
		return usuario;
		
	}
}
