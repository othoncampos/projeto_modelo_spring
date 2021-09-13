package br.edu.ifba.financeiro.api.dto;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.ifba.financeiro.api.model.AccountModel;
import lombok.Data;

public class Account2 {

	private Long id;
	
	private Integer id_pessoa;
	
	private String nome_pessoa;
	
	private String nome;
	
	private Integer id_banco;
	
	private Bank bank;
	
	private String nome_banco;
	
	private Integer tipo;
	
	private Integer numero;
	
	private Integer digito;
	
	private Double saldo;
	
	public static Account2 converter(AccountModel model) {
		var obj = new Account2();
		obj.setId( model.getId() );
		obj.setNome( model.getNome() );
//		obj.setId_pessoa( model.getId_pessoa() );
//		obj.setId_banco( model.getId_banco() );
		obj.setBank( Bank.converter( model.getBankModel() ) );
//		obj.setNome_banco(  model.getNome_banco() );
//		obj.setNome_pessoa(  model.getNome_pessoa() );
		obj.setTipo( model.getTipo() );
		obj.setNumero( model.getNumero() );
		obj.setDigito( model.getDigito() );
		obj.setSaldo( model.getSaldo() );
		return obj;
	}
	
	public static List<Account2> converter(List<AccountModel> models) {
		List<Account2> list = new ArrayList<Account2>();
		for (AccountModel model : models) {
			list.add( Account2.converter(model)) ;
		}
		return list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public Integer getId_banco() {
		return id_banco;
	}

	public void setId_banco(Integer id_banco) {
		this.id_banco = id_banco;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Integer getDigito() {
		return digito;
	}

	public void setDigito(Integer digito) {
		this.digito = digito;
	}

	public Double getSaldo() {
		return saldo;
	}
	
	public String getSaldoFormatado() {
		Locale localeBR = new Locale( "pt", "BR" );
		NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
		return dinheiroBR.format( this.getSaldo() );
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;	
	}
	
	public String getNome_pessoa() {
		return nome_pessoa;
	}

	public void setNome_pessoa(String nome_pessoa) {
		this.nome_pessoa = nome_pessoa;
	}

	public String getNome_banco() {
		return nome_banco;
	}

	public void setNome_banco(String nome_banco) {
		this.nome_banco = nome_banco;
	}
	
	public String getNumeroDigito() {
		return this.getNumero() + "-" + getDigito(); 
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getId().toString() + getNumero().toString();
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	
	
	  
}
