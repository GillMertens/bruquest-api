package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.dto.authDTO.CredentialsDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserCreateDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserUpdateDTO;
import com.bruquest.bruquestapi.exception.UserNotFoundException;
import com.bruquest.bruquestapi.model.User;
import com.bruquest.bruquestapi.repository.UserRepository;
import com.bruquest.bruquestapi.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.List;
import java.util.stream.Collectors;

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
    public UserDTO create(UserCreateDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.username());
        user.setPassword(passwordEncoder.encode(userDTO.password()));
        user.setEmail(userDTO.email());
        user.setRole("USER");
        userValidator.validate(user, false);
        User savedUser = userRepository.save(user);
        return UserDTO.toDTO(savedUser);
    }

    /**
     * Retrieves a User entity by its ID.
     *
     * @param id the ID of the User entity to be retrieved
     * @return the retrieved User entity
     * @throws UserNotFoundException if no User entity with the given ID is found
     */
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND));
        return UserDTO.toDTO(user);
    }

    /**
     * Retrieves all User entities.
     *
     * @return a list of all User entities
     */
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Updates a User entity and saves the changes in the database.
     *
     * @param user the User entity to be updated
     * @return the updated User entity
     * @throws UserNotFoundException if no User entity with the given ID is found
     */
    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id, HttpStatus.NOT_FOUND));
        existingUser.setUsername(userUpdateDTO.username());
        existingUser.setEmail(userUpdateDTO.email());
        existingUser.setPassword(passwordEncoder.encode(userUpdateDTO.password()));
        System.out.println(existingUser.getUsername());
        userValidator.validate(existingUser, true);
        User updatedUser = userRepository.save(existingUser);
        return UserDTO.toDTO(updatedUser);
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

    @Override
    public UserDTO login(CredentialsDTO credentialsDTO) {
        User user = userRepository.findByEmail(credentialsDTO.email())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + credentialsDTO.email(), HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()), user.getPassword())) {
            return UserDTO.toDTO(user);
        } else {
            throw new UserNotFoundException("The password was incorrect", HttpStatus.BAD_REQUEST);
        }
    }

}