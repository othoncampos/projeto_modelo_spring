package br.edu.ifba.financeiro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.edu.ifba.financeiro.api.model.PessoaModel;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long>{
	 List<PessoaModel> findByNomeContains(String name);
	 
	 @Query(value="select * from pessoa where email = :email and senha = :senha", nativeQuery = true)
	 public PessoaModel login(@Param("email") String email, @Param("senha") String senha);

}
