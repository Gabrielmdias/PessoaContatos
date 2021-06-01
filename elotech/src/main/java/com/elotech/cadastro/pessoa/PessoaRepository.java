package com.elotech.cadastro.pessoa;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Transactional
public interface PessoaRepository extends JpaRepository<Pessoa, Long>, JpaSpecificationExecutor<Pessoa> {

	@Query("SELECT obj FROM Pessoa obj JOIN FETCH obj.contatos WHERE obj IN :pessoas")
	List<Pessoa> findPessoasContatos(@Param("pessoas") List<Pessoa> pessoas);
}

