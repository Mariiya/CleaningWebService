package com.opsu.controllers.exception.web;

import org.springframework.http.ResponseEntity;
/**
 * This class is a creational design pattern, which allows constructing complex objects such as Response
 * @author group 183
 * @version 2.1
 */

public class ResponseEntityBuilder {

    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
