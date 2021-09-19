package com.me.microservices.util.http;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@Getter
@NoArgsConstructor(force = true)
public class HttpErrorInfo {
	private final ZonedDateTime timestamp;
	private final String path;
	private final HttpStatus httpStatus;
	private final String message;

	/**
	 * @param httpStatus http request status
	 * @param path       http request address
	 * @param message    error message
	 */
	public HttpErrorInfo(HttpStatus httpStatus, String path, String message) {
		timestamp = ZonedDateTime.now();
		this.httpStatus = httpStatus;
		this.path = path;
		this.message = message;
	}
}
