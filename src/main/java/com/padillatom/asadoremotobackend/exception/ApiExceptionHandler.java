package com.padillatom.asadoremotobackend.exception;

import com.padillatom.asadoremotobackend.util.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String CONSTRAINT_VIOLATIONS_MESSAGE = "Constraint Violations";

    /*
    * ============================================
    *           Authentication Exception
    * ============================================
    */
    @ExceptionHandler(value = {BadCredentialsException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiExceptionResponse> badCredentialsExceptionHandler(BadCredentialsException badCredentialsException) {
        var apiException = new ApiExceptionResponse(
                StringUtils.uppercaseFirstCharacter(badCredentialsException.getMessage()),
                HttpStatus.FORBIDDEN.value()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiException);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiExceptionResponse> usernameNotFoundExceptionHandler(UsernameNotFoundException usernameNotFoundException) {
        var apiException = new ApiExceptionResponse(
                StringUtils.uppercaseFirstCharacter(usernameNotFoundException.getMessage()),
                HttpStatus.FORBIDDEN.value()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiException);
    }

    /*
     * ============================================
     *             REST API Exception
     * ============================================
     */
    @ExceptionHandler(value = {NoSuchElementException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiExceptionResponse> noSuchElementExceptionHandler(NoSuchElementException noSuchElementException) {
        var apiException = new ApiExceptionResponse(
                StringUtils.uppercaseFirstCharacter(noSuchElementException.getMessage()),
                HttpStatus.NOT_FOUND.value()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiException);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiExceptionResponse> illegalArgumentExceptionHandler(IllegalArgumentException illegalArgumentException) {
        var apiException = new ApiExceptionResponse(
                illegalArgumentException.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        List<FieldError> errorFields = ex.getBindingResult().getFieldErrors();

        for (FieldError e : errorFields) {
            details.add(StringUtils.uppercaseFirstCharacter(e.getField()) + ": " + StringUtils.uppercaseFirstCharacter(e.getDefaultMessage()));
        }
        var apiConstraintViolationException = ApiConstraintViolationExceptionResponse.builder()
                .message(CONSTRAINT_VIOLATIONS_MESSAGE)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .errors(details)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiConstraintViolationException);
    }

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var apiException = new ApiExceptionResponse(
                ex.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiException);
    }

    /*
     * ============================================
     *             Application Exception
     * ============================================
     */
    @ExceptionHandler(value = {GenericExceptionResponse.class})
    public ResponseEntity<ApiExceptionResponse> genericExceptionHandler(GenericExceptionResponse genericExceptionResponse) {
        var apiException = new ApiExceptionResponse(
                StringUtils.uppercaseFirstCharacter(genericExceptionResponse.getMessage()),
                genericExceptionResponse.getHttpStatus().value()
        );
        return ResponseEntity.status(genericExceptionResponse.getHttpStatus()).body(apiException);
    }
}