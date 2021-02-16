package com.herval.javatie.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.herval.javatie.domain.model.Categoria;
import com.herval.javatie.domain.repository.CategoriaRepository;

public class CategoriaRepositoryImpl implements CategoriaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Categoria> listar() {
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}

	@Override
	public Categoria buscar(Long id) {
		Categoria categoria = manager.find(Categoria.class, id);
		if (categoria == null) {
			throw new IllegalArgumentException("Categoria não existe");
		}
		return categoria;
	}

	@Transactional
	@Override
	public Categoria salvar(Categoria categoria) {
		return manager.merge(categoria);
	}

	@Transactional
	@Override
	public void remover(Categoria categoria) {
		categoria = buscar(categoria.getId());
		manager.remove(categoria);
	}

}
