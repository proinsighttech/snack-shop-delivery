package com.proinsight.domain.exception;

public class IngredientNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public IngredientNotFoundException(String message) {
		super(message);
	}

	public IngredientNotFoundException(Long ingredientId) {
		super(String.format("Não existe um ingrediente com id %s", ingredientId));
	}
}
