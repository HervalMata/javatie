package com.herval.javatie.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.herval.javatie.domain.exception.EntidadeNaoEncontradaException;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements JpaRepository<T, ID> {
	
	private EntityManager manager;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}
	
	public Optional<T> buscarPrimeiro() {
		var jpql = "from " + getDomainClass().getName();
		T entity = manager.createQuery(jpql, getDomainClass())
				.setMaxResults(1).getSingleResult();
		return Optional.ofNullable(entity);
	}
	
	/*
	 * public T buscarOuFalhar(ID id) throws EntidadeNaoEncontradaException { return
	 * findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.
	 * format("Não existe um cadastro de %s com código %s", getDomainClass(), id)));
	 * }
	 */

}
