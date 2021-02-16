package com.herval.javatie.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomRepository<T, ID> extends JpaRepository<T, ID> {
	Optional<T> buscarPrimeiro();
}
