package com.zensar.stockapplication.exceptions;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(InvalidStockIdException.class)
	public ResponseEntity<CustomErrorResponse> handleStockError(HttpServletResponse response){
		
		
		CustomErrorResponse response1=new CustomErrorResponse();
		
		response1.setTimeStamp(LocalDateTime.now());
		response1.setStatus("400");
		response1.setError("Invalid Id");
		return new ResponseEntity<CustomErrorResponse>(response1,HttpStatus.BAD_REQUEST);
	}
}
