package com.darksun.RPGChallengeAPI.exception;

import java.io.Serial;

public abstract class InvalidInputException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 2930679669329858379L;

	public InvalidInputException( String message ) {
		super( message );
	}
}
