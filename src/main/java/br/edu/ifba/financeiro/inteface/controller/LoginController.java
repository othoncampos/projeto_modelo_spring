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
public class LoginController {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping("/login")
	public String index(){
		return "login/index";
	}
	
	@PostMapping("/loginto")
	public String loginto(Model model, String email, String senha){
		String erro = "";
		
		PessoaModel pm = this.pessoaRepository.login(email, senha);
		
		if (pm != null ) {
			Pessoa p = Pessoa.converter(pm);
			System.out.println(pm.getNome());
			return "redirect:/home";
		}
		
		erro = "Usuário ou senha inválidos!";
		model.addAttribute("erro", erro );
		
		return "login/index";
	}
}
