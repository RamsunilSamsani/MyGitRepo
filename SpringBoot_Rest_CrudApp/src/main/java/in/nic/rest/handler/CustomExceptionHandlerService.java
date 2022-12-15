package in.nic.rest.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.nic.rest.exception.ProductNotFoundException;
import in.nic.rest.model.ErrorData;

@RestControllerAdvice
public class CustomExceptionHandlerService {
	/*
	 * Below method is called by FC. When ProductNotFoundException
	 * is thrown by any Rest controller (after throwing exception)
	 */
	
	//--->Message comes in String format
/*	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException pne) {
		return new ResponseEntity<String>(pne.getMessage(),HttpStatus.NOT_FOUND);
	} */
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorData> handleProductNotFoundException(ProductNotFoundException pne) {
		return new ResponseEntity<ErrorData>(
				new ErrorData(pne.getMessage(),new Date().toString(),"Product"),HttpStatus.NOT_FOUND
				);
	}
}
