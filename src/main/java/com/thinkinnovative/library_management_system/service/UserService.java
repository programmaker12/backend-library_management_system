package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.dto.AuthRequestDTO;
import com.thinkinnovative.library_management_system.entity.User;

import java.util.Optional;

public interface UserService {

    /**
     * Registers a new user with the provided authentication request details.
     *
     * @param request the authentication request DTO containing registration data
     * @return the saved User entity
     */
    User registerUser(AuthRequestDTO request);

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the User if found, or empty otherwise
     */
    Optional<User> findByUsername(String username);
}
