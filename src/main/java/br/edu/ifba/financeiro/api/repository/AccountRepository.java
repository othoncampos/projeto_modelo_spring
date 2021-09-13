package br.edu.ifba.financeiro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifba.financeiro.api.model.AccountModel;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long>{
	
	@Query(value="SELECT c.id\r\n"
			+ "     , c.id_pessoa\r\n"
			+ "     , c.nome\r\n"
			+ "     , p.nome as nome_pessoa\r\n"
			+ "     , c.id_banco\r\n"
			+ "     , b.nome as nome_banco \r\n"
			+ "     , c.tipo\r\n"
			+ "     , c.numero\r\n"
			+ "     , c.digito\r\n"
			+ "     , c.saldo\r\n"
			+ "FROM demo_financeiro.conta as c\r\n"
			+ "join demo_financeiro.banco as b\r\n"
			+ "  on c.id_banco = b.id_banco\r\n"
			+ "join demo_financeiro.pessoa as p\r\n"
			+ "  on c.id_pessoa = p.id", nativeQuery = true)
	public List<AccountModel> findAll();
}
