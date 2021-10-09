package com.opsu.controllers;

import com.opsu.dao.UserDao;
import com.opsu.models.JwtResponse;
import com.opsu.models.LoginRequest;
import com.opsu.models.User;
import com.opsu.models.enumeration.Role;
import com.opsu.secutity.jwt.JwtUtils;
import com.opsu.secutity.services.UserDetailsImpl;
import com.opsu.secutity.services.UserDetailsServiceImpl;
import com.opsu.services.AuthorizationService;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    private static final Logger log = Logger.getLogger(AuthenticationController.class.getName());

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public AuthenticationController(AuthenticationManager authenticationManager, AuthorizationService authorizationService, UserDetailsServiceImpl userDetailsService, UserDao userDao, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userDao = userDao;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.debug("/signin " + loginRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Role role;
        String roleStr = String.valueOf(userDetails.getAuthorities());

        if ("[ROLE_SERVICE_PROVIDER]".equals(roleStr)) {
            role = Role.ROLE_SERVICE_PROVIDER;
        } else {
            role = Role.ROLE_CLIENT;
        }
        return ResponseEntity.ok(new JwtResponse(jwt, new User(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPassword(),
                role)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User signUpRequest) {
        if (userDao.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }

        // Create new user's account
        User user = new User(BigInteger.ONE, signUpRequest.getPhoneNumber(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(), signUpRequest.getRole());
        userDao.save(user);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/user/{id}")
    public User getUserById(@NotNull @PathVariable String id) {
        return userDetailsService.getUserById(new BigInteger(id));
    }

}


