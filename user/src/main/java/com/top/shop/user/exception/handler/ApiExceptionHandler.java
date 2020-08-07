package com.top.shop.user.exception.handler;

import java.io.IOException;

import com.top.shop.user.api.response.ErrorMessage;
import com.top.shop.user.exception.BadInputRequestException;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.exception.UserException;
import com.top.shop.user.exception.UserExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = {NumberFormatException.class , UserDoesntExit.class, UserExist.class, UserException.class, BadInputRequestException.class,Exception.class})
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
