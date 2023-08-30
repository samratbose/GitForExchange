package com.cook.exception;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cook.util.CookPickConstansts;

@RestControllerAdvice
public class CookGlobalExceptionHandler {
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(ProductNotFoundException.class)
	ResponseEntity<ErrorMessage> ProductNotFoundExceptionHandler(ProductNotFoundException ex){
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
		errorMessage.setMessage(environment.getProperty(CookPickConstansts.PRODUCT_NOT_FOUND.toString()));
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorMessage> ExceptionHandler(Exception ex){
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorMessage.setMessage(environment.getProperty(CookPickConstansts.SERVER_ERROR.toString()));
		return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<ErrorMessage> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		ErrorMessage message=new ErrorMessage();
		message.setErrorCode(HttpStatus.BAD_REQUEST.value());
		message.setMessage(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", ")));
		return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		
	}
	

}
