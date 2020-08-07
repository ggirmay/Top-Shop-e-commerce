package com.shop.top.gateway.gateway.exception.handler;

import com.shop.top.gateway.gateway.api.response.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;


@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { UsernameNotFoundException.class,NumberFormatException.class,NullPointerException.class, HttpClientErrorException.class})
	public ResponseEntity<ErrorMessage> handleBadInputRequestException(Exception ex) {
		String message = ex.getClass().getSimpleName() + " : " + ex.getMessage();
		ErrorMessage response = new ErrorMessage(message, "Make sure your input value is correct");

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(value = { IOException.class })
	public ResponseEntity<ErrorMessage> handleIoException(Exception ex) {
		String  message = "Server error, cause " + ex.getClass().getSimpleName() + " : " + ex.getMessage();
		ErrorMessage response = new ErrorMessage(message, "Try again in a few minutes");

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

}
