package com.darksun.RPGChallengeAPI.exception;

import java.io.Serial;

public class ResourceNotFoundException extends InvalidInputException {

	@Serial
	private static final long serialVersionUID = 1548599546573642648L;

	public ResourceNotFoundException( String message ) {
		super( message );
	}
}
