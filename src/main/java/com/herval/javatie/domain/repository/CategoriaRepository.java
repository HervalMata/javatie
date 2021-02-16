package com.herval.javatie.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herval.javatie.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> consultarPorNome(String nome);
}
