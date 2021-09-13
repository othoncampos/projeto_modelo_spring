package br.edu.ifba.financeiro.inteface.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index(){
		return "start/index";
	}
	
	@GetMapping("/home")
	public String index(Model model){
		model.addAttribute("nome","Othon Campos");
		model.addAttribute("ultimoAcesso","03/09/2021");
		model.addAttribute("router","/home");
		model.addAttribute("titlePage","Dashboard");
		return "home/index";
	}
	
}
