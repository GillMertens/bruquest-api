package com.bruquest.bruquestapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.bruquest.bruquestapi.exception.UserNotFoundException;
import com.bruquest.bruquestapi.model.User;
import com.bruquest.bruquestapi.repository.UserRepository;
import com.bruquest.bruquestapi.util.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void createUserSuccessfully() {
        User user = new User();
        user.setPassword("password");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.create(user);

        assertEquals("encodedPassword", result.getPassword());
        verify(userValidator).validate(user);
        verify(userRepository).save(user);
    }

    @Test
    public void getUserByIdSuccessfully() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        User result = userService.getUserById(user.getId());

        assertEquals(user, result);
    }

    @Test
    public void getUserByIdThrowsExceptionWhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    public void updateUserSuccessfully() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.updateUser(user);

        assertEquals(user, result);
        verify(userValidator).validate(user);
        verify(userRepository).save(user);
    }

    @Test
    public void updateUserThrowsExceptionWhenUserNotFound() {
        User user = new User();
        user.setId(1L);
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user));
    }

    @Test
    public void deleteUserSuccessfully() {
        User user = new User();
        user.setId(1L);
        lenient().when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(user.getId());

        userService.deleteUser(user.getId());

        verify(userRepository).deleteById(user.getId());
    }
}
