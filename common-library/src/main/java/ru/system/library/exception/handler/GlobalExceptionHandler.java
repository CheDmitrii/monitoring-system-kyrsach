package ru.system.library.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.system.library.exception.HttpResponseEntityException;

import java.util.Optional;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpResponseEntityException.class)
    public ResponseEntity<String> responseExceptionHandler(HttpResponseEntityException exception) {
        HttpHeaders httpHeaders = exception.getHttpHeaders();
        HttpStatus httpStatus = exception.getHttpStatus();
        String message = Optional.ofNullable(exception.getMessage()).orElse(httpStatus.getReasonPhrase());
        return new ResponseEntity<>(message, httpHeaders, httpStatus);
    }
}
