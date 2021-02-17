package com.herval.javatie.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EstadoNaoEncontradoException;
import com.herval.javatie.domain.exception.FormaPagamentoNaoEncontradaException;
import com.herval.javatie.domain.model.FormaPagamento;
import com.herval.javatie.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return formaPagamentoRepository.save(formaPagamento);
	}
	
	public void excluir(Long formaPagamentoId) {
		try {
			formaPagamentoRepository.deleteById(formaPagamentoId);
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(String.format("Não existe um cadastro de forma de pagamento com código %s", formaPagamentoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Forma de Pagamento com código %s não pode ser removida", formaPagamentoId));
		}
	}
	
	public FormaPagamento buscarOuFalhar(Long formaPagamentoId) {
		return formaPagamentoRepository.findById(formaPagamentoId).orElseThrow(() -> new EstadoNaoEncontradoException(formaPagamentoId));
	}
}
