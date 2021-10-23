package com.opsu.services;

import com.opsu.dao.UserDao;
import com.opsu.models.JwtResponse;
import com.opsu.models.LoginRequest;
import com.opsu.models.User;
import com.opsu.models.enumeration.Role;
import com.opsu.secutity.jwt.JwtUtils;
import com.opsu.secutity.services.UserDetailsImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.math.BigInteger;

@Service
public class AuthorizationService {
    private static Logger logger = Logger.getLogger(AuthorizationService.class.getName());
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationService(UserDao userDao, JwtUtils jwtUtils, AuthenticationManager authenticationManager) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
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
        return new JwtResponse(jwt, new User(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getPassword(),
                role));
    }

    public void registerUser(User userRequest) {
        if (userDao.existsByEmail(userRequest.getEmail())) {
            ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }

        // Create new user's account
        User user = new User(BigInteger.ONE, userRequest.getPhoneNumber(),
                userRequest.getEmail(),
                userRequest.getPassword(), userRequest.getRole());
        userDao.save(user);
    }

}
