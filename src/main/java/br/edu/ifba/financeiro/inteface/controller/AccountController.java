package br.edu.ifba.financeiro.inteface.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifba.financeiro.api.dto.Account;
import br.edu.ifba.financeiro.api.dto.Bank;
import br.edu.ifba.financeiro.api.model.AccountModel;
import br.edu.ifba.financeiro.api.repository.AccountRepository;
import br.edu.ifba.financeiro.api.repository.BankRepository;

@Controller
public class AccountController {
	
	private final String router_base = "/account";
	private final String folder_base = "/account";
	
	private List<Bank> banks = null;
	
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private BankRepository bankRepository;
	
	@GetMapping( router_base )
	public String index(Model model){
		List<Account> lista = Account.converter( repository.findAll() );
		model.addAttribute("accounts", lista);
		
		model.addAttribute("router","/account");
		model.addAttribute("titlePage","Lista de Contas");
		
		model.addAttribute("nome","Othon Campos");
		return folder_base + "/index";
	}
	
	@GetMapping( router_base + "/new")
	public String formNew(Model model){
		model.addAttribute("title", "Cadastro de Banco");
		model.addAttribute("subtitle", "Utilize essa tela para cadastrar bancos no sistema.");
		model.addAttribute("action", "new" );
		model.addAttribute("route", "/bank/insert" );
		Account obj = new Account();

		if ( banks == null )
			banks = Bank.converter( bankRepository.findAll() ) ;
		System.out.println( "Tamnho: "+  banks.size());
		model.addAttribute("banks"  , banks);
		model.addAttribute("account", obj );
		return folder_base + "/Form";
	}
	
	@GetMapping(router_base + "/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        AccountModel model = repository.getById(id);
        if ( model != null ) {
        	repository.delete( model );
        } else {
            throw new Exception("Banco Não encontrada");
        }
        return "redirect:" + router_base;
    }
	
	@PostMapping(router_base + "/insert")
	public String insert(Account account ){
		AccountModel model = new AccountModel();
		model.setId( account.getId() );
//		model.setId_pessoa( account.getId_pessoa() );
//		model.setId_banco( account.getId_banco() );
		model.setTipo( account.getTipo() );
		model.setNumero( account.getNumero() );
		model.setSaldo( account.getSaldo() );
		
		repository.save(model );
		return "redirect:" + router_base;
	}

	@GetMapping(router_base + "/view/{id}")
	public String formView(@PathVariable("id") Long id, Model model) throws Exception {
		model.addAttribute("title", "Visualização dos dados da Conta");
		model.addAttribute("subtitle", "Utilize essa tela para visualizar os dados da conta.");
		model.addAttribute("action", "view" );
		Account obj = Account.converter( repository.getById(id) );
		model.addAttribute("account", obj );
		return folder_base + "/View";
	}

	
	@GetMapping(router_base + "/alter/{id}")
	public String formAlter(@PathVariable("id") Long id, Model model){
		System.out.println("formAlter");
		model.addAttribute("title", "Visualização dos dados da Conta");
		model.addAttribute("subtitle", "Utilize essa tela para visualizar os dados da conta.");
		model.addAttribute("action", "update" );
		model.addAttribute("route", router_base + "/update" );
		Account obj = Account.converter( repository.getById(id) );
		
		if ( banks == null )
			banks = Bank.converter( bankRepository.findAll() ) ;
		
		
		System.out.println( "Tamnho: "+  banks.size());
		
		model.addAttribute("banks"  , banks);
		
		
		model.addAttribute("account", obj );
		return folder_base + "/Form";
	}
	
	@PostMapping(router_base + "/update")
	public String update(Account account){
		AccountModel model = repository.getById(account.getId());
		model.setId( account.getId() );
//		model.setId_pessoa( account.getId_pessoa() );
//		model.setId_banco( account.getId_banco() );
		model.setTipo( account.getTipo() );
		model.setNumero( account.getNumero() );
		model.setSaldo( account.getSaldo() );
		

		repository.save( model );
		return "redirect:" + router_base;
	}

}
