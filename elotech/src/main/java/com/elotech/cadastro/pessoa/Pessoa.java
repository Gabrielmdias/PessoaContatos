package com.elotech.cadastro.pessoa;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.elotech.contato.Contato;
import com.elotech.shared.BasicEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa extends BasicEntity {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nome;

	@CPF
	@NotBlank
	@Column(unique=true)
	private String cpf;

	@Past
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;

	@NotNull
	@Size(min=1)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "PESSOA_ID")
	private Set<Contato> contatos;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Set<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Set<Contato> contatos) {
		this.contatos = contatos;
	}
	
	public Pessoa atualizaDados(Pessoa pessoa) {

        this.setNome(pessoa.getNome());
        this.setDataNascimento(pessoa.getDataNascimento());
        this.setCpf(pessoa.getCpf());
        this.setContatos(pessoa.getContatos());

        return this;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}
