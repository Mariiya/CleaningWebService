package com.opsu.controllers;

import com.opsu.models.User;
import com.opsu.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@RestController
@Validated
@RequestMapping(value = "/auth/")
public class AuthenticationController {
    private final AuthorizationService service;

    @Autowired
    public AuthenticationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public User getUser(@NotNull @PathVariable String id) {
        return null;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody User signInRequest) throws AuthenticationException
    {
        return null;
    }

    @PostMapping("/logout")
    public void logout(@Valid @RequestBody User user)
    {

    }

}
