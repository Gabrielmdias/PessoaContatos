package com.elotech.contato;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Transactional
public interface ContatoRepository extends JpaRepository<Contato, Long>, JpaSpecificationExecutor<Contato> {
}

