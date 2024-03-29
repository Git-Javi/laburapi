package app.controller.exception;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class CustomExceptionHandler {

	// Producida por quebrantar las validaciones de javax.validation
	@ExceptionHandler(ConstraintViolationException.class)
	public void handleConstraintViolationException(ConstraintViolationException cve, ServletWebRequest webRequest) throws IOException {
		webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), cve.getMessage());
	}

	// Producida por quebrantar la integridad referencial de las entidades
	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public void handleHibernateConstraintViolationException(org.hibernate.exception.ConstraintViolationException hcve, ServletWebRequest webRequest)
			throws IOException {
		webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), hcve.getMessage());
	}

	// Producida por un formato inválido en la fecha-hora
	@ExceptionHandler(InvalidFormatException.class)
	public void handleInvalidFormatException(InvalidFormatException dtpe, ServletWebRequest webRequest) throws IOException {
		webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), dtpe.getMessage());
	}

	// Producida por un campo no conocido para el ObjectMapper
	@ExceptionHandler(UnrecognizedPropertyException.class)
	public void UnrecognizedPropertyExceptionException(UnrecognizedPropertyException upe, ServletWebRequest webRequest) throws IOException {
		webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), upe.getMessage());
	}

	// Producida cuando no se encuentra la entidad sobre la que mappear las relaciones
	@ExceptionHandler(EntityNotFoundException.class)
	public void EntityNotFoundExceptionExceptionException(EntityNotFoundException enf, ServletWebRequest webRequest) throws IOException {
		webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), enf.getMessage());
	}
}
