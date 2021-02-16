package com.herval.javatie.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.herval.javatie.domain.exception.EntidadeEmUsoException;
import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;
import com.herval.javatie.domain.model.Categoria;
import com.herval.javatie.domain.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria salvar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public void excluir(Long categoriaId) {
		try {
			categoriaRepository.deleteById(categoriaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de categoria com código %s", categoriaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Categoria com código %s não pode ser removida", categoriaId));
		}
	}
}
