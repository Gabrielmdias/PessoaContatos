package com.elotech.cadastro.pessoa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;

	@Autowired
	public PessoaService(PessoaRepository pessoaRepository) {
		this.pessoaRepository = pessoaRepository;
	}

	@Transactional(readOnly = true)
	public List<Pessoa> findAll() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		pessoaRepository.findPessoasContatos(pessoas);
		return pessoas;
	}

	@Transactional(readOnly = true)
	public Page<Pessoa> findAll(Pageable pageable) {
		Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);
		pessoaRepository.findPessoasContatos(pessoas.stream().collect(Collectors.toList()));
		return pessoas;
	}

	@Transactional(readOnly = true)
	public Optional<Pessoa> findById(Long id) {
		return pessoaRepository.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Long id) {
		if (pessoaRepository.existsById(id)) {
			pessoaRepository.deleteById(id);
		}
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Pessoa create(Pessoa pessoa) {
        return save(pessoa);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Pessoa update(Pessoa pessoa) {
    	//problema ao atulizar os dados cujo contato já existe
    	Pessoa pessoaRecuperada = pessoaRepository.findById(pessoa.getId())
                .map(p -> p.atualizaDados(pessoa))
                .orElseThrow(() -> new RuntimeException("Não é possivel editar uma pessoa sem id!"));

        return save(pessoaRecuperada);
    }
    
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Pessoa save(Pessoa entity) {
		return pessoaRepository.save(entity);
	}

}