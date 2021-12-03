package com.paulo.syservice.rosources.exption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.paulo.syservice.service.exption.DataIntegratyViolationExption;
import com.paulo.syservice.service.exption.ObjectNotFaundExption;

@RestControllerAdvice
public class ResourceExceptionHandeler {
	
	@ExceptionHandler(ObjectNotFaundExption.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFaundExption e){
		StandardError error= new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegratyViolationExption.class)
	public ResponseEntity<StandardError> objectNotFoundException(DataIntegratyViolationExption e){
		StandardError error= new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> objectNotFoundException(MethodArgumentNotValidException e){
		
		ValidationError error =new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro na validação dos campos!");
		
		for(FieldError x: e.getBindingResult().getFieldErrors()) {
			error.addErrors(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
