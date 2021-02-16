package com.herval.javatie.domain.repository;

import java.util.List;

import com.herval.javatie.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Long id);
}
