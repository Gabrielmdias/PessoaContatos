package com.elotech.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.elotech.cadastro.pessoa.Pessoa;

public class PessoaDTO {

	private Long id;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private List<ContatoDTO> contatos;

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public PessoaDTO() {
	}

	public PessoaDTO(Long id, String nome, String cpf, String dataNascimento, List<ContatoDTO> contatos) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.contatos = contatos;
	}

	public PessoaDTO(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento().format(formatter);
		this.contatos = pessoa.getContatos().stream().map(contato -> new ContatoDTO(contato))
				.collect(Collectors.toList());
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<ContatoDTO> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoDTO> contatos) {
		this.contatos = contatos;
	}

}
