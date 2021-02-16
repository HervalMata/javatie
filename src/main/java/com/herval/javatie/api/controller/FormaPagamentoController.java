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
import com.herval.javatie.domain.model.FormaPagamento;
import com.herval.javatie.domain.repository.FormaPagamentoRepository;
import com.herval.javatie.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas_pagamento")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@GetMapping
	public List<FormaPagamento> listar() {
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId) {
		Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
		if (formaPagamento.isPresent()) {
			return ResponseEntity.ok(formaPagamento.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody FormaPagamento formaPagamento) {
		try {
			formaPagamento = formaPagamentoService.salvar(formaPagamento);
			return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamento);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formaPagamento);
		}
	}
	
	@PutMapping("/{permissaoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento) {
		try {
			Optional<FormaPagamento> formaPagamentoAtualOptional = formaPagamentoRepository.findById(formaPagamentoId);
			if (formaPagamentoAtualOptional.isPresent()) {
				FormaPagamento formaPagamentoAtual = formaPagamentoAtualOptional.get();
				BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
				FormaPagamento formaPagamentoSalva = formaPagamentoService.salvar(formaPagamentoAtual);
				return ResponseEntity.ok(formaPagamentoSalva);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(formaPagamento);
		}
		
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamento> remover(@PathVariable Long formaPagamentoId) {
		try {
			formaPagamentoService.excluir(formaPagamentoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}  catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}	
	}
}
