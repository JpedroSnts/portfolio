package dev.jpedrosnts.portifolio.controller.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<StandardError> erroNaValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		List<String> erros = new ArrayList<>();
		for (ObjectError err : ex.getAllErrors()) {
			erros.add(err.getDefaultMessage());
		}
        StandardError err = new StandardError(
        		Instant.now(),
        		"Erro na validação",
        		erros,
        		status.value(),
        		request.getRequestURI().toString()
        );
        return ResponseEntity.status(status).body(err);
	}
}