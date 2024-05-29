package com.proinsight.domain.exception;

public class SequentialCodeNotFoundException extends EntityNotFoundException{

	private static final long serialVersionUID = 1L;

	public SequentialCodeNotFoundException(String category){
        super(String.format("Não existe uma sequência com a categori %d", category));
	}
}
