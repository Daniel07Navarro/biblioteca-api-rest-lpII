package com.daniel.biblioteca_lpII.exception;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllExeption(Exception ex, WebRequest req) {
		ErrorResponse error = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ModelNotFoundException.class) //INDICAR EL NOMBRE DE EXCEPCION
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req){
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(SQLException.class) //INDICAR EL NOMBRE DE EXCEPCION
    public ResponseEntity<ErrorResponse> handleSQLException(ModelNotFoundException ex, WebRequest req){
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
        return new ResponseEntity<>(err, HttpStatus.CONFLICT);
    }

	//PARA ARGUMENTOS NO VALIDOS
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		var message = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(error -> error.getField() + ": "+error.getDefaultMessage())
				.collect(Collectors.joining(" ; "));
		ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(),message,request.getDescription(false));
		return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorResponse err = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}

	/*
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var message = ex.getBindingResult().getFieldErrors()
                .stream().map(error -> error.getField() +": "+error.getDefaultMessage())
                .collect(Collectors.joining(" ; "));

        ErrorResponse error = new ErrorResponse(LocalDateTime.now(),message,request.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest req) {
		 ErrorResponse err = new ErrorResponse(LocalDateTime.now(),ex.getMessage(),req.getDescription(false));
	     return new ResponseEntity<>(err,HttpStatus.NOT_FOUND);
	}*/

}
