package com.opsu.controllers.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.opsu.controllers.exception.web.ApiError;
import com.opsu.controllers.exception.web.ResponseEntityBuilder;
import com.opsu.exceptions.EmptyDataBaseException;
import javassist.NotFoundException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for global exception handling
 * Sends custom ApiError to Front-end
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<String>();
        details = ex.getBindingResult()
                .getFieldErrors()
                .stream()

                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                details, BigInteger.valueOf(909));

        return ResponseEntityBuilder.build(err);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                Collections.singletonList("Not Readable Format"), BigInteger.valueOf(15L));
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception) {

        List<String> details = exception.getConstraintViolations().stream().map(error -> error.getMessageTemplate()).collect(Collectors.toList());
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                details, BigInteger.valueOf(101L));
        return ResponseEntityBuilder.build(err);

    }

    @ExceptionHandler({PermissionDeniedDataAccessException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(PermissionDeniedDataAccessException exception) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                Collections.singletonList("Sorry, You can not access this data"), BigInteger.valueOf(101L));
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exception) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Validation Errors",
                Collections.singletonList(exception.getMessage()), BigInteger.valueOf(101L));
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException exception) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Incorrect Password",
                Collections.singletonList(exception.getMessage()), BigInteger.valueOf(403L));
        return ResponseEntityBuilder.build(err);
    }


    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleDaoAccessException(NotFoundException exception){
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Bad Credentials",
                Collections.singletonList(exception.getMessage()), BigInteger.valueOf(403L));
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({EmptyDataBaseException.class})
    public ResponseEntity<Object> handleDaoAccessException(EmptyDataBaseException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                errors,
                BigInteger.valueOf(501));
        return ResponseEntityBuilder.build(err);
    }

    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity<ApiError> handleDaoAccessException(UsernameNotFoundException ex, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());

        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "DAO ACCESS EXCEPTION: " + ex.getMessage(),
                errors, BigInteger.valueOf(401L)), headers, status, request);
    }

    @ExceptionHandler({NullPointerException.class})
    protected ResponseEntity<ApiError> handleNullPointerException(NullPointerException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return handleExceptionInternal(ex, new ApiError(LocalDateTime.now(), status, "NULL POINTER", errors, BigInteger.valueOf(501L)), headers, status, request);
    }


    protected ResponseEntity<ApiError> handleExceptionInternal(Exception ex, ApiError body, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }


    @ExceptionHandler({JsonProcessingException.class})
    public final ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex, WebRequest request) {
        ApiError err = new ApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                "Parse exception",
                Collections.singletonList("Cant parse map to json"),
                BigInteger.valueOf(80999));
        return ResponseEntityBuilder.build(err);
    }
}

