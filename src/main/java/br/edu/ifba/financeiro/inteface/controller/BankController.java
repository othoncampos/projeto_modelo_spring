package br.edu.ifba.financeiro.inteface.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifba.financeiro.api.dto.Bank;
import br.edu.ifba.financeiro.api.model.BankModel;
import br.edu.ifba.financeiro.api.repository.BankRepository;

@Controller
public class BankController {
	
	@Autowired
	private BankRepository bankRepository;
	
	@GetMapping("/bank")
	public String index(Model model){
		List<Bank> lista = Bank.converter( bankRepository.findAll() );
		model.addAttribute("banks", lista);
		model.addAttribute("nome","Othon Campos");
		model.addAttribute("router","/bank");
		model.addAttribute("titlePage","Lista de Bancos");
		return "bank/index";
	}
	
	@GetMapping("/bank/new")
	public String formNew(Model model){
		model.addAttribute("title", "Cadastro de Banco");
		model.addAttribute("subtitle", "Utilize essa tela para cadastrar bancos no sistema.");
		model.addAttribute("action", "new" );
		model.addAttribute("route", "/bank/insert" );
		Bank b = new Bank();
		model.addAttribute("bank", b );
		return "bank/Form";
	}
	
	@GetMapping("/bank/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        BankModel p = bankRepository.getById(id);
        if ( p != null ) {
        	bankRepository.delete( p );
        } else {
            throw new Exception("Banco Não encontrada");
        }
        return "redirect:/bank";
    }
	
	@PostMapping("/bank/insert")
	public String insert(BankModel bankDTO){
		System.out.println("insert: " + bankDTO.getNome());
		BankModel bm = new BankModel();
		bm.setId_banco( bankDTO.getId_banco() );
		bm.setNome( bankDTO.getNome() );
		bm.setUrl( bankDTO.getUrl() );
		
		bankRepository.save( bm );
		return "redirect:/bank";
	}

	@GetMapping("/bank/view/{id}")
	public String formView(@PathVariable("id") Long id, Model model) throws Exception {
		System.out.println("formView");
		model.addAttribute("title", "Visualização dos dados da Pessoa");
		model.addAttribute("subtitle", "Utilize essa tela para visualizar os dados da pessoas.");
		model.addAttribute("action", "view" );
		Bank b = Bank.converter( bankRepository.getById(id) );
		model.addAttribute("bank", b );
		return "bank/View";
	}

	
	@GetMapping("/bank/alter/{id}")
	public String formAlter(@PathVariable("id") Long id, Model model){
		System.out.println("formAlter");
		model.addAttribute("title", "Modificacao dos dados de uma Pessoa");
		model.addAttribute("subtitle", "Utilize essa tela para modificar os dados de uma pessoas.");
		model.addAttribute("action", "update" );
		model.addAttribute("route", "/bank/update" );
		Bank b = Bank.converter( bankRepository.getById(id) );
		model.addAttribute("bank", b );
		return "bank/Form";
	}
	
	@PostMapping("/bank/update")
	public String update(Bank bank){
		System.out.println("update: " + bank.getNome());
		BankModel bm = bankRepository.getById(bank.getId_banco());
		bm.setNome( bank.getNome() );
		bm.setUrl( bank.getUrl() );
		bankRepository.save( bm );
		return "redirect:/bank";
	}

}
