package br.edu.ifba.financeiro.inteface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifba.financeiro.api.dto.Pessoa;
import br.edu.ifba.financeiro.api.model.PessoaModel;
import br.edu.ifba.financeiro.api.repository.PessoaRepository;

@Controller
public class SignupController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping("/signup")
	public String index(){
		return "signup/index";
	}
	
	@PostMapping("/signup/create")
	public String create(String nome, String email, String senha){
		PessoaModel pm = new PessoaModel();
		pm.setNome(nome);
		pm.setEmail(email);
		pm.setSenha(senha);
		pessoaRepository.save(pm);
		return "redirect:/login";
	}
}
