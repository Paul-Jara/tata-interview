/**
 * 
 */
package com.tata.accountservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 990540427809485987L;

	public NotAllowedException(String message) {
		super(message);
	}
}
