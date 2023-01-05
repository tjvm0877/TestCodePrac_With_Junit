package com.hyun.TestCodePrac.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hyun.TestCodePrac.dto.response.CMResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> apiExceptionHandler(RuntimeException e) {
		CMResponseDto response = CMResponseDto.builder().result("fail").msg(e.getMessage()).data(null).build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> validException(MethodArgumentNotValidException e) {
		String errorMsg = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		CMResponseDto response = CMResponseDto.builder().result("fail").msg(errorMsg).data(null).build();
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
