package br.edu.ifba.financeiro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.financeiro.api.model.BankModel;

@Repository
public interface BankRepository extends JpaRepository<BankModel, Long>{
	 List<BankModel> findByNomeContains(String name);
}
