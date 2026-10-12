/**
 * 
 */
package com.tata.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {

	private static final long serialVersionUID = 3537971926456935158L;

	public InternalException(String message) {
		super(message);
	}
}
