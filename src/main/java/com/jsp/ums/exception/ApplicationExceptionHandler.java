package com.jsp.ums.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.ums.utility.ErrorStructure;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
	
	
//		@Autowired
//		private ErrorStructure<String> errorStructure;
		
//		@ExceptionHandler(UserNotFoundByIdException.class)
//		public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(UserNotFoundByIdException exception){
//			errorStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			errorStructure.setMessege(exception.getMessage());
//			errorStructure.setRootCause("The Request user given with Id not found...!");
//			return new ResponseEntity<ErrorStructure<String>>(errorStructure,HttpStatus.NOT_FOUND);
//		}
		
	
	private ResponseEntity<Object> structure(HttpStatus status,String messege,Object rootCause){
		return new ResponseEntity<Object> (Map.of(
				"status",status.value(),
				"messege",messege,
				"rootCause",rootCause
				),status);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> allErrors = ex.getAllErrors();
		Map<String,String> errors=new HashMap<String,String>();
		allErrors.forEach(error->{
			FieldError fieldError=(FieldError)error;
			errors.put(fieldError.getField(),fieldError.getDefaultMessage());
		});
		return structure(HttpStatus.BAD_REQUEST,"Failed to Save Data",errors);
	}
	
	
	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException exception){
		return structure(HttpStatus.NOT_FOUND, exception.getMessage(), "User Not found with given Id");
	}
	public ResponseEntity<Object> handleRuntime(RuntimeException exception){
		return structure(HttpStatus.BAD_REQUEST, exception.getMessage(), "Illegal Argument");
	}
		
}
