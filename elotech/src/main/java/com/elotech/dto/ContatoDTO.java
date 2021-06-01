package com.elotech.dto;

import com.elotech.contato.Contato;

public class ContatoDTO {

	private Long id;
	private String nome;
	private String telefone;
	private String email;

	public ContatoDTO() {
	}

	public ContatoDTO(Long id, String nome, String telefone, String email) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public ContatoDTO(Contato contato) {
		this.id = contato.getId();
		this.nome = contato.getNome();
		this.telefone = contato.getTelefone();
		this.email = contato.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
