package edu.jong.server.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.jong.server.exception.BadParameterException;
import edu.jong.server.exception.ConvertFailEnumException;
import edu.jong.server.exception.DuplicateResourceException;
import edu.jong.server.exception.NotFoundResourceException;
import edu.jong.server.model.ErrorResponse;

@RestControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler({
		BadParameterException.class,
		ConvertFailEnumException.class,
		DuplicateResourceException.class,
		NotFoundResourceException.class,
		Exception.class
	})
	ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {
		
		HttpStatus status = null;
		
		if (ex instanceof BadParameterException) {
			status = HttpStatus.BAD_REQUEST;
		} else if (ex instanceof ConvertFailEnumException) {
			status = HttpStatus.BAD_REQUEST;
		} else if (ex instanceof DuplicateResourceException) {
			status = HttpStatus.CONFLICT;
		} else if (ex instanceof NotFoundResourceException) {
			status = HttpStatus.NOT_FOUND;
		} else {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return ResponseEntity.status(status)
				.body(ErrorResponse.builder()
						.message(ex.getMessage()).build());
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		return super.handleExceptionInternal(ex, 
				ErrorResponse.builder()
				.message(ex.getMessage()).build()
				, headers, status, request);
	}
}
