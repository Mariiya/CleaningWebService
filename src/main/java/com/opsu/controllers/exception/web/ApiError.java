package com.opsu.controllers.exception.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Custom Error-response for system error
 */
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private HttpStatus status;
    private String message;
    private List<String> errors;
    private BigInteger errorCode;

    public ApiError(LocalDateTime timestamp, HttpStatus status, String message, List<String> errors,BigInteger
                    errorCode) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.errorCode=errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public BigInteger getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(BigInteger errorCode) {
        this.errorCode = errorCode;
    }
}
