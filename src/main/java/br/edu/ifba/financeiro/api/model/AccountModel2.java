package br.edu.ifba.financeiro.api.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ifba.financeiro.api.dto.Bank;
import lombok.Data;

@Data
@Entity
@Table(name="conta")
public class AccountModel2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="id_pessoa", nullable = false)
	private Integer id_pessoa;
	
	private String nome_pessoa;
	
	@Column(name="nome", nullable = false)
	private String nome;
	
//	@Column(name="id_banco", nullable = false)
//	private Integer id_banco;
	
	private String nome_banco;
	
    @ManyToOne
    @JoinColumn(name = "id_banco")
	private BankModel bankModel;
	
	@Column(name="tipo", nullable = false)
	private Integer tipo;
	
	@Column(name="numero", nullable = false)
	private Integer numero;
	
	@Column(name="digito", nullable = false)
	private Integer digito;
	
	@Column(name="saldo")
	private Double saldo;

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
		return getBankModel().getNome();
//		return nome_banco;
	}

	public void setNome_banco(String nome_banco) {
		this.nome_banco = nome_banco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BankModel getBankModel() {
		return bankModel;
	}

	public void setBankModel(BankModel bankModel) {
		this.bankModel = bankModel;
	}
	
	
	  
}
