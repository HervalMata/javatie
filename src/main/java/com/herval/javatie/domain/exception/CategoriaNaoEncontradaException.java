package com.herval.javatie.domain.exception;

public class CategoriaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradaException(String message) {
		super(message);
	}
	
	public CategoriaNaoEncontradaException(Long categoriaId) {
		this(String.format("Não existe um cadastro de categoria com código %s", categoriaId));
	}

}
