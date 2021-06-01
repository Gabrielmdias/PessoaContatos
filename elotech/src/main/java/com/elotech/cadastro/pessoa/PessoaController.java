package com.elotech.cadastro.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll() {
		List<Pessoa> objects = service.findAll();

		if (!objects.isEmpty()) {
			return ResponseEntity.ok().body(objects);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<Pessoa>> findById(@PathVariable Long id) {
		Optional<Pessoa> obj = service.findById(id);

		if (obj.isPresent()) {
			return ResponseEntity.ok().body(obj);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = { "search" })
	public Page<Pessoa> search(@RequestParam(value = "search", required = false) String searchOp,
			@Qualifier("page") Pageable pageable) {
		return service.findAll(pageable);
	}

	@PostMapping
	public ResponseEntity<Pessoa> insert(@RequestBody Pessoa obj) {
		Pessoa saved = service.create(obj);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@PutMapping
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa recurso) {
		Pessoa updted = this.service.update(recurso);
		return ResponseEntity.status(HttpStatus.CREATED).body(updted);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
