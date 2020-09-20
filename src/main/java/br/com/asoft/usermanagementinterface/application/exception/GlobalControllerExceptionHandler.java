package br.com.asoft.usermanagementinterface.application.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import br.com.asoft.usermanagementinterface.model.exception.CustomExceptionDTO;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ResponseEntity<CustomExceptionDTO> handleCustomSimpleException(ValidationException validationException) {
		HttpStatus responseStatus = validationException.getHttpStatus();
		return new ResponseEntity<>(new CustomExceptionDTO(validationException.getDescription()), responseStatus);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public ResponseEntity<CustomExceptionDTO> handleCustomSimpleException(HttpMessageNotReadableException ex) {
		String msgErro = "";

		if (ex.getCause() instanceof InvalidFormatException) {
			msgErro = "Erro ao tentar converter: '" + ((InvalidFormatException) ex.getCause()).getValue();
			String tipo = ((InvalidFormatException) ex.getCause()).getTargetType().toString();

			/**
			 * Logica para nao expor a classe do enum que nao pode ser convertida. E assim
			 * evitar expor parte da implementacao interna da aplicacao
			 */
			if (!tipo.toUpperCase().contains("ENUM")) {
				msgErro += "' para o tipo: " + tipo;
			} else {
				msgErro += "' O valor informado não é válido. Por favor consulte o manual da API";
			}

		} else {
			msgErro = ex.getCause().getMessage();
		}

		return new ResponseEntity<>(new CustomExceptionDTO(msgErro), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseEntity<CustomExceptionDTO> handleCustomSimpleException(ConstraintViolationException ex) {
		List<String> erroList = new ArrayList<>();

		ex.getConstraintViolations().forEach(
				exception -> erroList.add(exception.getPropertyPath().toString() + " - " + exception.getMessage()));
		String erroMsg = "Erro ao tentar gravar objeto recebido: " + erroList;

		return new ResponseEntity<>(new CustomExceptionDTO(erroMsg), HttpStatus.BAD_REQUEST);
	}

}
