package com.opsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/auth/")
public class AuthenticationController {

    @Autowired
    public AuthenticationController() {
    }

    @RequestMapping(value="login",method= RequestMethod.POST)
    public ResponseEntity<?> login() throws AuthenticationException
    {
        return null;
    }

    @RequestMapping(value="logout",method= RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }

}
