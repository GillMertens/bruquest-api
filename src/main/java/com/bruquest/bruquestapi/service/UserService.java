package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.model.User;
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
    User create(User user);

    /**
     * Retrieves a User entity by its ID.
     *
     * @param id the ID of the User entity to be retrieved
     * @return the retrieved User entity
     */
    User getUserById(Long id);

    /**
     * Retrieves all User entities.
     *
     * @return a list of all User entities
     */
    List<User> getAllUsers();

    /**
     * Updates a User entity.
     *
     * @param user the User entity to be updated
     * @return the updated User entity
     */
    User updateUser(User user);

    /**
     * Deletes a User entity by its ID.
     *
     * @param id the ID of the User entity to be deleted
     */
    void deleteUser(Long id);
}
