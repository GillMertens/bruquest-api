package com.bruquest.bruquestapi.util;

import com.bruquest.bruquestapi.exception.UserValidationException;
import com.bruquest.bruquestapi.model.User;
import com.bruquest.bruquestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    private final UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validate(User user) throws RuntimeException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new UserValidationException("Username cannot be empty.");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new UserValidationException("Password cannot be empty.");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new UserValidationException("Email cannot be empty.");
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            throw new UserValidationException("Role cannot be empty.");
        }

        if (!user.getEmail().contains("@")) {
            throw new UserValidationException("Email must be valid.");
        }

        if (user.getPassword().length() < 8) {
            throw new UserValidationException("Password must be at least 8 characters.");
        }

        if (!user.getRole().equals("ADMIN") && !user.getRole().equals("USER")) {
            throw new UserValidationException("Role must be either ADMIN or USER.");
        }

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserValidationException("Username already exists.");
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserValidationException("Email already exists.");
        }
    }
}
