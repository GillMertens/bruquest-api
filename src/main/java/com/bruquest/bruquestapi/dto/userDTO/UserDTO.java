package com.bruquest.bruquestapi.dto.userDTO;

import com.bruquest.bruquestapi.model.User;

public record UserDTO(
        Long id,
        String username,
        String email,
        String role
) {
    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }

    public void setToken(String token) {
    }
}
