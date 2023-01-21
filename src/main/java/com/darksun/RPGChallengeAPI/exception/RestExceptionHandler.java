package com.darksun.RPGChallengeAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class RestExceptionHandler {

	public ResponseEntity< ErrorMessage > handle( Exception ex, HttpStatus statusCode,
												  String description ) {
		ErrorMessage message = new ErrorMessage( statusCode, LocalDateTime.now( ), ex.getMessage( ),
												 description );
		ex.printStackTrace( );
		return new ResponseEntity<>( message, message.getStatusCode( ) );
	}

	@ExceptionHandler( ResourceNotFoundException.class )
	public ResponseEntity< ErrorMessage > resourceNotFoundHandler( Exception ex,
																   WebRequest request ) {
		return handle( ex, HttpStatus.NOT_FOUND, request.getDescription( false ) );
	}

	@ExceptionHandler( { InvalidInputException.class } )
	public ResponseEntity< ErrorMessage > InvalidInputHandler( Exception ex, WebRequest request ) {
		return handle( ex, HttpStatus.BAD_REQUEST, request.getDescription( false ) );
	}

	@ExceptionHandler( Exception.class )
	@ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR )
	public ResponseEntity< ErrorMessage > globalExceptionHandler( Exception ex,
																  WebRequest request ) {
		return handle( ex, HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription( false ) );
	}
}
