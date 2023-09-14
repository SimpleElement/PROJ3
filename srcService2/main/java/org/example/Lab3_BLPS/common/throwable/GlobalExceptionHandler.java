package org.example.Lab3_BLPS.common.throwable;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Map;

import static org.example.Lab3_BLPS.common.util.ResponseUtil.constraintErrors;
import static org.example.Lab3_BLPS.common.util.ResponseUtil.getErrorBody;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = getErrorBody(status, request, "get_parameters_validation");
        body.put("messages", constraintErrors(ex.getBindingResult()));
        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = getErrorBody(status, request, "body_parameters_validation");
        body.put("messages", constraintErrors(ex.getBindingResult()));
        return new ResponseEntity<>(body, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> body = getErrorBody(status, request, "body_error");
        body.put("messages", List.of("Ошбибка тела запроса"));
        return new ResponseEntity<>(body, status);
    }
}
