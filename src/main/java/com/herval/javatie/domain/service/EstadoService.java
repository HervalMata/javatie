package com.herval.javatie.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;
import com.herval.javatie.domain.model.Estado;
import com.herval.javatie.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código %s", estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Estado com código %s não pode ser removida", estadoId));
		}
	}
}
