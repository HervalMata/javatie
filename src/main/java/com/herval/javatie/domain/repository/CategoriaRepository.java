package com.herval.javatie.domain.repository;

import java.util.List;

import com.herval.javatie.domain.model.Categoria;

public interface CategoriaRepository {

	List<Categoria> listar();
	Categoria buscar(Long id);
	Categoria salvar(Categoria categoria);
	void remover(Long id);
}
