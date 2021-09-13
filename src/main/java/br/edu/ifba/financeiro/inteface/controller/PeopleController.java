package br.edu.ifba.financeiro.inteface.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ifba.financeiro.api.dto.Pessoa;
import br.edu.ifba.financeiro.api.model.PessoaModel;
import br.edu.ifba.financeiro.api.repository.PessoaRepository;

@Controller
public class PeopleController {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping("/people")
	public String index(Model model){
		List<Pessoa> lista = Pessoa.converter( pessoaRepository.findAll() );	
		model.addAttribute("pessoas", lista);
		model.addAttribute("nome","Othon Campos");
		model.addAttribute("router","/people");
		model.addAttribute("titlePage","Lista de Pessoas");
		
		//		<a th:href="@{router}" th:text="${titlePage}" >Dashboard</a>
		
		model.addAttribute("nome","Othon Campos");
		model.addAttribute("nome","Othon Campos");
		return "people/index";
	}
	
	@GetMapping("/people/new")
	public String formNew(Model model){
		model.addAttribute("title", "Cadastro de Pessoa");
		model.addAttribute("subtitle", "Utilize essa tela para cadastrar pessoas no sistema.");
		model.addAttribute("action", "new" );
		model.addAttribute("route", "/people/insert" );
		Pessoa pessoa = new Pessoa();
		model.addAttribute("pessoa", pessoa );
		return "people/Form";
	}
	
	@GetMapping("/people/delete/{id}")
    public String delete(@PathVariable("id") Long id) throws Exception {
        PessoaModel p = pessoaRepository.getById(id);
        if ( p != null ) {
            pessoaRepository.delete( p );
        } else {
            throw new Exception("Pessoa Não encontrada");
        }
        return "redirect:/people";
    }
	
	@PostMapping("/people/insert")
	public String insert(PessoaModel pessoaDTO){
		System.out.println("insert: " + pessoaDTO.getNome());
		PessoaModel pessoaM = new PessoaModel();
		pessoaM.setId( pessoaDTO.getId() );
		pessoaM.setNome( pessoaDTO.getNome() );
		pessoaM.setEmail( pessoaDTO.getEmail() );
		pessoaM.setSenha( pessoaDTO.getSenha() );
		pessoaRepository.save( pessoaM );
		return "redirect:/people";
	}

	@GetMapping("/people/view/{id}")
	public String formView(@PathVariable("id") Long id, Model model) throws Exception {
		System.out.println("formView");
		model.addAttribute("title", "Visualização dos dados da Pessoa");
		model.addAttribute("subtitle", "Utilize essa tela para visualizar os dados da pessoas.");
		model.addAttribute("action", "view" );
		Pessoa pessoa = Pessoa.converter( pessoaRepository.getById(id) );
		model.addAttribute("pessoa", pessoa );
		return "people/View";
	}

	
	@GetMapping("/people/alter/{id}")
	public String formAlter(@PathVariable("id") Long id, Model model){
		System.out.println("formAlter");
		model.addAttribute("title", "Modificacao dos dados de uma Pessoa");
		model.addAttribute("subtitle", "Utilize essa tela para modificar os dados de uma pessoas.");
		model.addAttribute("action", "update" );
		model.addAttribute("route", "/people/update" );
		Pessoa pessoa = Pessoa.converter( pessoaRepository.getById(id) );
		model.addAttribute("pessoa", pessoa );
		return "people/Form";
	}
	
	@PostMapping("/people/update")
	public String update(Pessoa pessoa){
		System.out.println("update: " + pessoa.getNome());
		PessoaModel pessoaM = pessoaRepository.getById(pessoa.getId());
		pessoaM.setNome( pessoa.getNome() );
		pessoaM.setEmail( pessoa.getEmail() );
		pessoaRepository.save( pessoaM );
		return "redirect:/people";
	}

}
