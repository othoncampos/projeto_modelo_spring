package br.edu.ifba.financeiro.api.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import br.edu.ifba.financeiro.api.model.PessoaModel;

public class Pessoa {

	private Long id;
	private String nome;
	private String email;
	private String senha;

	public static Pessoa converter(PessoaModel pm) {
		var pessoa = new Pessoa();
		pessoa.setId( pm.getId() );
		pessoa.setNome( pm.getNome() );
		pessoa.setEmail( pm.getEmail() );
		pessoa.setSenha( pm.getSenha() );
		return pessoa;
	}
	
	public static List<Pessoa> converter(List<PessoaModel> pessoas) {
		List<Pessoa> list=new ArrayList<Pessoa>();
		for (PessoaModel model : pessoas) {
			list.add( Pessoa.converter(model)) ;
		}
		return list;
		// return clientes.stream().map( Cliente :: converter).collect(Collectors.toList());
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
