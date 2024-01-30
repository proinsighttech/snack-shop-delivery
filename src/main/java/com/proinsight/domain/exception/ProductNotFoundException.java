package com.proinsight.domain.exception;

public class ProductNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Long snackShopId, Long productId) {
		this(String.format("Não existe um cadastro de produto com código %d para a lanchonete de código %d",
				productId, snackShopId));
	}

	public ProductNotFoundException(Long productId) {
		this(String.format("Não existe um cadastro de produto com código %d ", productId));
	}
}
