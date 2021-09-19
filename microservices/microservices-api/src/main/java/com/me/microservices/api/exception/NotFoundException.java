package com.me.microservices.api.exception;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException {
	/**
	 * @param message error message
	 */
	public NotFoundException(String message) {
		super(message);
	}

	/**
	 * @param message error message
	 * @param cause   origin of exception
	 */
	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause origin of exception
	 */
	public NotFoundException(Throwable cause) {
		super(cause);
	}
}
