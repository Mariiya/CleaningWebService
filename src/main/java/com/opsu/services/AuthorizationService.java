package com.opsu.services;

import com.opsu.dao.UserDao;
import com.opsu.exceptions.EmptyDataBaseException;
import com.opsu.models.*;
import com.opsu.models.enumeration.Role;
import com.opsu.secutity.jwt.JwtUtils;
import com.opsu.secutity.services.UserDetailsImpl;
import javassist.NotFoundException;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.math.BigInteger;

@Service
public class AuthorizationService {
    private static Logger logger = Logger.getLogger(AuthorizationService.class.getName());
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private NotificationService notificationService;

    @Autowired
    public AuthorizationService(UserDao userDao, JwtUtils jwtUtils, AuthenticationManager authenticationManager, NotificationService notificationService) {
        this.userDao = userDao;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.notificationService = notificationService;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) throws NotFoundException {
        if (!userDao.existsByEmail(loginRequest.getEmail())) {
            throw new NotFoundException("User with such email is not registered in the system");
        }
        Authentication authentication;

        authentication = authenticationManager.authenticate(
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
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                role));
    }


    public boolean newPasswordAfterReset(String email) throws NotFoundException, EmptyDataBaseException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with this email is not registered in the system");
        }
        String newPassword = RandomString.make(10);
        String newPasswordEncoded = DigestUtils.sha256Hex(newPassword);
        user.setPassword(newPasswordEncoded);
        userDao.update(user);
       return notificationService.newPasswordNotification(user,newPassword);
    }
    public boolean changeUserPassword(UserDetailsImpl updater, User user) throws NotFoundException, EmptyDataBaseException {
        if (!updater.getId().equals(user.getId())) {
            throw new PermissionDeniedDataAccessException("Can not change this user password", new IllegalAccessError());
        }
        User userFromDB = userDao.getUserById(updater.getId());
        userFromDB.setPassword(user.getPassword());
        userDao.update(userFromDB);
        return true;
    }

    public String resetPassword(String email) throws NotFoundException, IOException, MessagingException, EmptyDataBaseException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User with this email is not registered in the system");
        }
         return notificationService.changePasswordNotification(user);
    }

    public Boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }

    public User registerUser(User userRequest) throws IOException, MessagingException, NotFoundException, EmptyDataBaseException {
        if (existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        User user = new User(BigInteger.ONE, userRequest.getPhoneNumber(),
                userRequest.getEmail(),
                userRequest.getPassword(), userRequest.getRole());
        userDao.save(user);
        user = userDao.findByEmail(user.getEmail());
        if (user != null) {
            userRequest.setId(user.getId());
            notificationService.sendRegistrationNotification(userRequest);
        }
        return user;
    }

    public boolean updateUser(UserDetailsImpl updater, User user) throws NotFoundException, EmptyDataBaseException {
        if (!updater.getId().equals(user.getId())) {
            throw new PermissionDeniedDataAccessException("Can not change this user password", new IllegalAccessError());
        }
        User userFromDB = userDao.getUserById(updater.getId());
        userFromDB.setPhoneNumber(user.getPhoneNumber());
        userFromDB.setEmail(user.getEmail());
        userDao.update(userFromDB);
        return true;
    }

    public User getUserById(BigInteger userId) throws NotFoundException {
        return userDao.getUserById(userId);
    }

}
