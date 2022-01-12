package com.br.lead.desafioLEAD.config.validation;

public class ErrorFormDto {
	private String Campo;
	private String error;
	
	public ErrorFormDto(String campo, String error) {
		super();
		Campo = campo;
		this.error = error;
	}

	public String getCampo() {
		return Campo;
	}

	public String getErro() {
		return error;
	}
	
	
}
