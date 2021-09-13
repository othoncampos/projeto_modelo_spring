package br.edu.ifba.financeiro.api.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.financeiro.api.model.BankModel;

public class Bank {

	private Long id_banco;
	private String nome;
	private String url;

	public static Bank converter(BankModel bm) {
		var bank = new Bank();
		bank.setId_banco( bm.getId_banco() );
		bank.setNome( bm.getNome() );
		bank.setUrl( bm.getUrl() );
		return bank;
	}
	
	public static List<Bank> converter(List<BankModel> banks) {
		List<Bank> list=new ArrayList<Bank>();
		for (BankModel model : banks) {
			list.add( Bank.converter(model)) ;
		}
		return list;
		// return clientes.stream().map( Cliente :: converter).collect(Collectors.toList());
	}

	

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
