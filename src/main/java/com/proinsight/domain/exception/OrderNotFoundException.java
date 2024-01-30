package com.proinsight.domain.exception;

public class OrderNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public OrderNotFoundException(String message) {
		super(message);
	}

	public OrderNotFoundException(Long orderCode) {
		super(String.format("Não existe um pedido com código %s", orderCode));
	}
}
