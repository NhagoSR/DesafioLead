package com.br.lead.desafioLEAD.controller.dto;

public class ErroDto {
	private String mensagem;

	
	public ErroDto(String mensagem) {
		super();
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
