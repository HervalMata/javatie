package com.herval.javatie.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;
import com.herval.javatie.domain.model.Cidade;
import com.herval.javatie.domain.model.Estado;
import com.herval.javatie.domain.repository.CidadeRepository;
import com.herval.javatie.domain.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		if (estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de esatdo com código %s", estadoId));
		}
		cidade.setEstado(estado.get());
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com código %s", cidadeId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cidade com código %s não pode ser removida", cidadeId));
		}
	}
}
