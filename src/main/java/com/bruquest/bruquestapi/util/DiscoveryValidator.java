package com.bruquest.bruquestapi.util;

import com.bruquest.bruquestapi.model.Discovery;
import com.bruquest.bruquestapi.exception.DiscoveryNotValidException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DiscoveryValidator {

    public void validate(Discovery discovery) {
        if (discovery == null) {
            throw new DiscoveryNotValidException("Discovery cannot be null");
        }

        if (discovery.getUser() == null || discovery.getUser().getId() == null) {
            throw new DiscoveryNotValidException("Discovery must have a valid user");
        }

        if (discovery.getLandmark() == null || discovery.getLandmark().getId() == null) {
            throw new DiscoveryNotValidException("Discovery must have a valid landmark");
        }

        if (discovery.getDate() == null || discovery.getDate().isAfter(LocalDateTime.now())) {
            throw new DiscoveryNotValidException("Discovery must have a valid date");
        }
    }
}