package com.bruquest.bruquestapi.dto.userDTO;

public record UserUpdateDTO(
        String username,
        String password,
        String email
) {
}
