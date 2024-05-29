package com.proinsight.domain.exception;

public class SnackShopNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public SnackShopNotFoundException(String message) {
		super(message);
	}

	public SnackShopNotFoundException(Long snackShopId) {
		this(String.format("Não existe um cadastro de lanchonete com o código %d.", snackShopId));
	}
}
