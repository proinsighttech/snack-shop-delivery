package com.proinsight.domain.exception;

public class PermissionNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public PermissionNotFoundException(String message) {
		super(message);
	}

	public PermissionNotFoundException(Long permissaoId) {
		this(String.format("Não existe um cadastro de permissão com código %d", permissaoId));
	}
}
