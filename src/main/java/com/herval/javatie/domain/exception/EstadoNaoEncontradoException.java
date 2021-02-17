package com.herval.javatie.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de estado com código %s", estadoId));
	}

}
