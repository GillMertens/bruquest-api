package com.bruquest.bruquestapi.util;

import com.bruquest.bruquestapi.exception.UserValidationException;
import com.bruquest.bruquestapi.model.User;
import com.bruquest.bruquestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(User user, boolean isUpdate) throws RuntimeException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new UserValidationException("Username cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new UserValidationException("Password cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserValidationException("Email cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new UserValidationException("Role cannot be empty.", HttpStatus.BAD_REQUEST);
        }

        if (!user.getEmail().contains("@")) {
            throw new UserValidationException("Email must be valid.", HttpStatus.BAD_REQUEST);
        }

        if (user.getPassword().length() < 8) {
            throw new UserValidationException("Password must be at least 8 characters.", HttpStatus.BAD_REQUEST);
        }

        if (!user.getRole().equals("ADMIN") && !user.getRole().equals("USER")) {
            throw new UserValidationException("Role must be either ADMIN or USER.", HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent() && (!isUpdate || !existingUser.get().getId().equals(user.getId()))) {
            throw new UserValidationException("Username " + user.getUsername() + " already exists.", HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingEmailUser = userRepository.findByEmail(user.getEmail());
        if (existingEmailUser.isPresent() && (!isUpdate || !existingEmailUser.get().getId().equals(user.getId()))) {
            throw new UserValidationException("Email " + user.getEmail() + " already exists.", HttpStatus.BAD_REQUEST);
        }
    }
}
