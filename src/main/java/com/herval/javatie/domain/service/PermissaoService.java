package com.herval.javatie.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;
import com.herval.javatie.domain.model.Permissao;
import com.herval.javatie.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	private PermissaoRepository permissaoRepository;
	
	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	public void excluir(Long permissaoId) {
		try {
			permissaoRepository.deleteById(permissaoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de permissão com código %s", permissaoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Permissão com código %s não pode ser removida", permissaoId));
		}
	}
}
