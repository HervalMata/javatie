package com.herval.javatie.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;
import com.herval.javatie.domain.model.Estado;
import com.herval.javatie.domain.repository.EstadoRepository;
import com.herval.javatie.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
		estado = estadoService.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		try {
			Optional<Estado> estadoAtualOptional = estadoRepository.findById(estadoId);
			if (estadoAtualOptional.isPresent()) {
				Estado estadoAtual = estadoAtualOptional.get();
				BeanUtils.copyProperties(estado, estadoAtual, "id");
				Estado estadoSalvo = estadoService.salvar(estadoAtual);
				return ResponseEntity.ok(estadoSalvo);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(estado);
		}
		
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {
		try {
			estadoService.excluir(estadoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}  catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}	
	}
}
