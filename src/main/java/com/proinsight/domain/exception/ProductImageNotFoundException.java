package com.proinsight.domain.exception;

public class ProductImageNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public ProductImageNotFoundException(String message) {
		super(message);
	}

	public ProductImageNotFoundException(Long snackShopId, Long productId) {
		this(String.format("Não existe um cadastro de foto do produto com código %d para a lanchonete de código %d",
				productId, snackShopId));
	}
}
