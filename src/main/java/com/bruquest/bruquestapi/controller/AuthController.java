package com.bruquest.bruquestapi.controller;

import com.bruquest.bruquestapi.config.UserAuthProvider;
import com.bruquest.bruquestapi.dto.authDTO.CredentialsDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserCreateDTO;
import com.bruquest.bruquestapi.dto.userDTO.UserDTO;
import com.bruquest.bruquestapi.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UserServiceImpl userService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserServiceImpl userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody CredentialsDTO credentialsDTO) {
        UserDTO userDTO = userService.login(credentialsDTO);
        userDTO.setToken(userAuthProvider.createToken(userDTO));
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO userDTO) {
        UserDTO newUser = userService.create(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
