package ru.system.library.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Getter
public class HttpResponseEntityException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final HttpHeaders httpHeaders;

    public HttpResponseEntityException(@NonNull HttpStatus httpStatus, @NonNull String message) {
        this(httpStatus, null, message);
    }

    public HttpResponseEntityException(@NonNull HttpStatus httpStatus, HttpHeaders httpHeaders, @NonNull String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.httpHeaders = httpHeaders;
    }
}
