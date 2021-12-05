package com.opsu.controllers;

import com.opsu.exceptions.EmptyDataBaseException;
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

/**
 * Class for users sign-in/sign-up
 */
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

    /**
     * – /api/auth/signin
     * authenticate { username, pasword }
     * update SecurityContext using Authentication object
     * generate JWT
     * get UserDetails from Authentication object
     * response contains JWT and UserDetails data
     * @param loginRequest
     * @return
     * @throws NotFoundException
     */
    @PostMapping("/auth/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws NotFoundException {
        return ResponseEntity.ok(authorizationService.authenticateUser(loginRequest));
    }

    @PostMapping("/change-password")
    public boolean changeUserPassword(@AuthenticationPrincipal UserDetailsImpl updater, @Valid @RequestBody User user) throws NotFoundException, IOException, MessagingException, EmptyDataBaseException {
        return authorizationService.changeUserPassword(updater, user);
    }

    @PostMapping("/auth/reset-password")
    public ResponseEntity<PasswordCode> changeUserPassword(@RequestParam String email) throws NotFoundException, IOException, MessagingException, EmptyDataBaseException {
        return ResponseEntity.ok(authorizationService.resetPassword(email));
    }

    @PostMapping("/auth/new-password")
    public boolean getPasswordAfterReset(@RequestParam String email) throws NotFoundException, IOException, MessagingException, EmptyDataBaseException {
        return authorizationService.newPasswordAfterReset(email);
    }

    /**
     * – /api/auth/signup
     *
     * check existing username/email
     * create new Vendor
     * save Vendor to database using VendorRepository
     * @param vendor
     * @return
     * @throws IOException
     * @throws MessagingException
     * @throws NotFoundException
     * @throws EmptyDataBaseException
     */
    @PostMapping("/auth/signup/vendor")
    public ResponseEntity<?> createVendor(@Valid @RequestBody Vendor vendor) throws IOException, MessagingException, NotFoundException, EmptyDataBaseException {
        if (authorizationService.existsByEmail(vendor.getEmail())) {
            ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        authorizationService.registerUser(vendor);
        Boolean success = vendorService.create(vendor);
        return ResponseEntity.ok(success);
    }

    /**
     * – /api/auth/signup
     * check existing username/email
     * create new Consumer
     * save Consumer to database using Consumer  Repository
     * @param consumer
     * @return
     * @throws IOException
     * @throws MessagingException
     * @throws NotFoundException
     * @throws EmptyDataBaseException
     */
    @PostMapping("/auth/signup/consumer")
    public ResponseEntity<?> createConsumer(@Valid @RequestBody Consumer consumer) throws IOException, MessagingException, NotFoundException, EmptyDataBaseException {
        if (authorizationService.existsByEmail(consumer.getEmail())) {
            ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        authorizationService.registerUser(consumer);
        Boolean success = consumerService.create(consumer);
        return ResponseEntity.ok(success);
    }

    @GetMapping("/user/{id}")
    public User getUserById(@NotNull @PathVariable String id) {
        return userDetailsService.getUserById(new BigInteger(id));
    }

}


