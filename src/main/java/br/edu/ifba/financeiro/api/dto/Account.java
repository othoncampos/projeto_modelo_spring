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

public class Account {

	private Long id;
	
//	private Integer id_pessoa;
	
	private Pessoa pessoa;
	
	private String nome;
	
//	private Integer id_banco;
	
	private Bank bank;
	
	private Integer tipo;
	
	private Integer numero;
	
	private Integer digito;
	
	private Double saldo;
	
	public static Account converter(AccountModel model) {
		var obj = new Account();
		obj.setId( model.getId() );
		obj.setNome( model.getNome() );
		obj.setBank( Bank.converter( model.getBankModel() ) );
		obj.setPessoa( Pessoa.converter( model.getPessoaModel() ) );
		obj.setTipo( model.getTipo() );
		obj.setNumero( model.getNumero() );
		obj.setDigito( model.getDigito() );
		obj.setSaldo( model.getSaldo() );
		return obj;
	}
	
	public static List<Account> converter(List<AccountModel> models) {
		List<Account> list = new ArrayList<Account>();
		for (AccountModel model : models) {
			list.add( Account.converter(model)) ;
		}
		return list;
	}

	public Account() {
		this.bank = new Bank();
		this.pessoa = new Pessoa();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public Integer getId_pessoa() {
//		return id_pessoa;
//	}
//
//	public void setId_pessoa(Integer id_pessoa) {
//		this.id_pessoa = id_pessoa;
//	}

//	public Integer getId_banco() {
//		return id_banco;
//	}
//
//	public void setId_banco(Integer id_banco) {
//		this.id_banco = id_banco;
//	}

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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	
	
	
	
	  
}
