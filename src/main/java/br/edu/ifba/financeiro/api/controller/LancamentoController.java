package br.edu.ifba.financeiro.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/teste")
public class LancamentoController {

	@GetMapping
	public String teste() {
		return "Teste1";
	}
	
}
