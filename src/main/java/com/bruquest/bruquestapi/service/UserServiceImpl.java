package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.exception.UserNotFoundException;
import com.bruquest.bruquestapi.model.User;
import com.bruquest.bruquestapi.repository.UserRepository;
import com.bruquest.bruquestapi.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserServiceImpl is a service class that implements the UserService interface.
 * It provides methods to perform operations on User entities.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    /**
     * Constructs a new UserServiceImpl with the given UserRepository, PasswordEncoder, and UserValidator.
     *
     * @param userRepository the UserRepository to be used
     * @param passwordEncoder the PasswordEncoder to be used
     * @param userValidator the UserValidator to be used
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
    }

    /**
     * Creates a new User entity and saves it in the database.
     *
     * @param user the User entity to be created
     * @return the created User entity
     */
    @Override
    public User create(User user) {
        user.setRole("USER");
        userValidator.validate(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Retrieves a User entity by its ID.
     *
     * @param id the ID of the User entity to be retrieved
     * @return the retrieved User entity
     * @throws UserNotFoundException if no User entity with the given ID is found
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    /**
     * Retrieves all User entities.
     *
     * @return a list of all User entities
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates a User entity and saves the changes in the database.
     *
     * @param user the User entity to be updated
     * @return the updated User entity
     * @throws UserNotFoundException if no User entity with the given ID is found
     */
    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getId()));
        userValidator.validate(user);
        return userRepository.save(existingUser);
    }

    /**
     * Deletes a User entity by its ID.
     *
     * @param id the ID of the User entity to be deleted
     */
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}