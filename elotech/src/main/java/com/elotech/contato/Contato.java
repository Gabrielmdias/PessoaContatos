package com.elotech.contato;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.elotech.cadastro.pessoa.Pessoa;
import com.elotech.shared.BasicEntity;

@Entity
@Table(name = "TB_CONTATO")
public class Contato extends BasicEntity {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nome;

	@NotBlank
	@Column(unique=true)
	private String telefone;

	@NotBlank
	@Email(message = "email inválido!")
	@Column(unique=true)
	private String email;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

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

	public Contato atualizaDados(Contato contato) {

        this.setNome(contato.getNome());
        this.setEmail(contato.getEmail());
        this.setTelefone(contato.getTelefone());

        return this;
    }
	
}
