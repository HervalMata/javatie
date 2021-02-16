package com.herval.javatie.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.herval.javatie.domain.model.Categoria;

@Component
public class CadastroCategoria {

	@PersistenceContext
	private EntityManager manager;
	
	public List<Categoria> listar() {
		return manager.createQuery("from Categoria", Categoria.class).getResultList();
	}
	
	@Transactional
	public Categoria salvar(Categoria categoria) {
		return manager.merge(categoria);
	}

	public Categoria buscar(long id) {
		return manager.find(Categoria.class, id);
	}

	public void remover(Categoria categoria) {
		categoria = buscar(categoria.getId());
		manager.remove(categoria);
	}
}
