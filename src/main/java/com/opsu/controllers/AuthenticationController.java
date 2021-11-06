package com.opsu.controllers;

import com.opsu.models.*;
import com.opsu.secutity.services.UserDetailsImpl;
import com.opsu.secutity.services.UserDetailsServiceImpl;
import com.opsu.services.AuthorizationService;
import com.opsu.services.ConsumerService;
import com.opsu.services.VendorService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigInteger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthenticationController {
    private static final Logger log = Logger.getLogger(AuthenticationController.class.getName());

    private final AuthorizationService authorizationService;
    private final ConsumerService consumerService;
    private final VendorService vendorService;
    private final UserDetailsServiceImpl userDetailsService;


    public AuthenticationController(AuthorizationService authorizationService1, ConsumerService consumerService,
                                    VendorService vendorService, UserDetailsServiceImpl userDetailsService) {
        this.authorizationService = authorizationService1;
        this.consumerService = consumerService;
        this.vendorService = vendorService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.debug("/signin " + loginRequest);
        return ResponseEntity.ok(authorizationService.authenticateUser(loginRequest));
    }

    @PostMapping("/change-password")
    public boolean changeUserPassword(@AuthenticationPrincipal UserDetailsImpl updater, @Valid @RequestBody User user) throws NotFoundException, IOException, MessagingException {
        return authorizationService.changeUserPassword(updater, user);
    }

    @PostMapping("/auth/signup/vendor")
    public ResponseEntity<?> createVendor(@Valid @RequestBody Vendor vendor) throws IOException, MessagingException, NotFoundException {
        if (authorizationService.existsByEmail(vendor.getEmail())) {
            ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        authorizationService.registerUser(vendor);
        vendorService.create(vendor);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/auth/signup/consumer")
    public ResponseEntity<?> createConsumer(@Valid @RequestBody Consumer consumer) throws IOException, MessagingException, NotFoundException {
        if (authorizationService.existsByEmail(consumer.getEmail())) {
            ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        authorizationService.registerUser(consumer);
        consumerService.create(consumer);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/user/{id}")
    public User getUserById(@NotNull @PathVariable String id) {
        return userDetailsService.getUserById(new BigInteger(id));
    }

}


