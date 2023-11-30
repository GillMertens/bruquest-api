package com.bruquest.bruquestapi.exception;

public class LandmarkNotFoundException extends RuntimeException{
    public LandmarkNotFoundException(String message) {
        super(message);
    }
}
