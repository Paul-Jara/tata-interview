/**
 * 
 */
package com.tata.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 990540427809485987L;

	public NotFoundException(String message) {
		super(message);
	}
}
