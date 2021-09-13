package br.edu.ifba.financeiro.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="banco")
public class BankModel {

	@Id
	@Column(name = "id_banco")
	private Long id_banco;
	
	@Column(name="nome", nullable = false)
	private String nome;
	
	@Column(name="url", nullable = false, unique = true)
	private String url;

	public Long getId_banco() {
		return id_banco;
	}

	public void setId_banco(Long id_banco) {
		this.id_banco = id_banco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
