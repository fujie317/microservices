package com.me.microservices.api.exception;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException {

	/**
	 * @param message error message
	 */
	public InvalidInputException(String message) {
		super(message);
	}

	/**
	 * @param message error message
	 * @param cause   root cause of exception
	 */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause root cause of exception
	 */
	public InvalidInputException(Throwable cause) {
		super(cause);
	}
}
