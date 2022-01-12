package com.br.lead.desafioLEAD.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.br.lead.desafioLEAD.model.Usuario;

public class UsuarioDto {
	private int id;
	private int idade;
	private String nome;
	private String email;
	private LocalDate data_nascimento;
	private Boolean privilegios_adm;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.idade = usuario.getIdade();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.data_nascimento = usuario.getData_nascimento();
		this.privilegios_adm = usuario.getPrivilegios_adm();
		
	}
	public int getId() {
		return id;
	}
	public int getIdade() {
		return idade;
	}
	public String getNome() {
		return nome;
	}
	public String getEmail() {
		return email;
	}
	public LocalDate getData_nascimento() {
		return data_nascimento;
	}
	public Boolean getPrivilegios_adm() {
		return privilegios_adm;
	}
	
	public static List<UsuarioDto> converter(List<Usuario> usuarios){
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
}
