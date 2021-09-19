package com.me.microservices.util.http;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.me.microservices.api.exception.InvalidInputException;
import com.me.microservices.api.exception.NotFoundException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Fujie Zhang
 *
 * @since Aug 13, 2021
 */
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
	/**
	 * @param request http request
	 * @param ex      exception to handle
	 * @return an HttpErrorInfo object
	 */
	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody HttpErrorInfo handleNotFoundExceptions(ServerHttpRequest request, NotFoundException ex) {

		return createHttpErrorInfo(NOT_FOUND, request, ex);
	}

	/**
	 * @param request http request
	 * @param ex      exception to handle
	 * @return an HttpErrorInfo object
	 */
	@ResponseStatus(UNPROCESSABLE_ENTITY)
	@ExceptionHandler(InvalidInputException.class)
	public @ResponseBody HttpErrorInfo handleInvalidInputException(ServerHttpRequest request,
			InvalidInputException ex) {

		return createHttpErrorInfo(UNPROCESSABLE_ENTITY, request, ex);
	}

	private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, ServerHttpRequest request, Exception ex) {

		final String path = request.getPath().pathWithinApplication().value();
		final String message = ex.getMessage();

		log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
		return new HttpErrorInfo(httpStatus, path, message);
	}
}
