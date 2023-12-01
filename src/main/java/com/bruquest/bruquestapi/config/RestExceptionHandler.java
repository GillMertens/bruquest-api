package com.bruquest.bruquestapi.config;

import com.bruquest.bruquestapi.dto.ErrorDTO;
import com.bruquest.bruquestapi.exception.UserNotFoundException;
import com.bruquest.bruquestapi.exception.UserValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler(value = UserValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleUserValidationException(UserValidationException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(new ErrorDTO(e.getMessage()));
    }

}
