package com.bruquest.bruquestapi.dto.userDTO;

public record UserCreateDTO(
    String username,
    String password,
    String email
) {
}
