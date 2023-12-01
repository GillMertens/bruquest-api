package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.dto.authDTO.CredentialsDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserCreateDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserUpdateDTO;

import java.util.List;

/**
 * UserService is an interface that defines the contract for the UserServiceImpl.
 * It provides methods to perform operations on User entities.
 */
public interface UserService {

    /**
     * Creates a new User entity.
     *
     * @param user the User entity to be created
     * @return the created User entity
     */
    UserDTO create(UserCreateDTO user);

    /**
     * Retrieves a User entity by its ID.
     *
     * @param id the ID of the User entity to be retrieved
     * @return the retrieved User entity
     */
    UserDTO getUserById(Long id);

    /**
     * Retrieves all User entities.
     *
     * @return a list of all User entities
     */
    List<UserDTO> getAllUsers();

    /**
     * Updates a User entity.
     *
     * @param user the User entity to be updated
     * @return the updated User entity
     */
    UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO);

    /**
     * Deletes a User entity by its ID.
     *
     * @param id the ID of the User entity to be deleted
     */
    void deleteUser(Long id);

    UserDTO login(CredentialsDTO credentialsDTO);
}
