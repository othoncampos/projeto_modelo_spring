package br.edu.ifba.financeiro.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.financeiro.api.model.PessoaModel;
import br.edu.ifba.financeiro.api.dto.Pessoa;
import br.edu.ifba.financeiro.api.repository.PessoaRepository;
import java.util.stream.Collectors;
//

@RestController
@RequestMapping( "/pessoa")
public class PessoaController {
	
//	@Autowired
	private PessoaRepository pessoaRepository;
	public PessoaController( PessoaRepository pessoaRepository ) {
		this.pessoaRepository = pessoaRepository;
	}
	
	@GetMapping("/")
	public List<Pessoa> list(){
		// Lista de PessoaModel 
		var pessoas =  pessoaRepository.findAll();
		
		//Criando uma lista de Objeto PessoaDTO. Objeto apenas para transferencia de informacao. 
		List<Pessoa> list=new ArrayList<Pessoa>();
		for (PessoaModel model : pessoas) {
			list.add( Pessoa.converter(model)) ;
		}
		
		return list;
		// return clientes.stream().map( Cliente :: converter).collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
    public Pessoa findById(@PathVariable("id") Long id) {
        var cli = pessoaRepository.getById(id);
        return Pessoa.converter(cli);
    }
	
	@PostMapping("/")
	@ResponseStatus( HttpStatus.CREATED )
	public Pessoa insert(@RequestBody Pessoa pessoaDTO){
		PessoaModel pessoaM = new PessoaModel( );
		pessoaM.setId( pessoaDTO.getId() );
		pessoaM.setNome( pessoaDTO.getNome() );
		pessoaM.setEmail( pessoaDTO.getEmail() );
		pessoaM.setSenha( pessoaDTO.getSenha() );
		return pessoaDTO.converter( pessoaRepository.save( pessoaM )) ;
	}
	
    @PutMapping("/{id}")
    @ResponseStatus( HttpStatus.CREATED )
    public void update(@PathVariable("id") Long id, @RequestBody Pessoa pessoa) throws Exception {
        var p = pessoaRepository.findById(id);

        if (p.isPresent()) {
            var pessoaSave = p.get();
            pessoaSave.setNome(pessoa.getNome());
            pessoaRepository.save(pessoaSave);
        } else {
            throw new Exception("Pessoa Não encontrada");
        }
    }
    

    @GetMapping("/filter")
    public List<Pessoa> findByName(@RequestParam("name") String name) {
        return this.pessoaRepository.findByNomeContains(name)
                .stream()
                .map(Pessoa::converter)
                .collect(Collectors.toList());
    }
    
	/*
    @GetMapping("/filter/custom")
    public List<PessoaRs> findPersonByCustom(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "sobrenome", required = false) String sobrenome
    ) {
        return this.pessoaCustomRepository.find(id, name, sobrenome)
                .stream()
                .map(PessoaRs::converter)
                .collect(Collectors.toList());
    }
	 
	 */
	
	
	
}

