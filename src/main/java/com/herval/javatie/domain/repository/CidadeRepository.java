package com.herval.javatie.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herval.javatie.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
