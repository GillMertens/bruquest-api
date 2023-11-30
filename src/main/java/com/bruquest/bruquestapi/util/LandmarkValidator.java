package com.bruquest.bruquestapi.util;

import com.bruquest.bruquestapi.exception.LandmarkValidationException;
import com.bruquest.bruquestapi.model.Landmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LandmarkValidator {

    @Autowired
    public LandmarkValidator() {
    }

    public void Validate(Landmark landmark) throws RuntimeException {
        if (landmark.getName() == null || landmark.getName().isEmpty()) {
            throw new LandmarkValidationException("Name cannot be empty.");
        }

        if (landmark.getDescription() == null || landmark.getDescription().isEmpty()) {
            throw new LandmarkValidationException("Description cannot be empty.");
        }

        if (landmark.getLongitude() < -180 || landmark.getLongitude() > 180) {
            throw new LandmarkValidationException("Longitude must be between -180 and 180.");
        }

        if (landmark.getLatitude() < -90 || landmark.getLatitude() > 90) {
            throw new LandmarkValidationException("Latitude must be between -90 and 90.");
        }

        if (landmark.getDifficulty() < 1 || landmark.getDifficulty() > 5) {
            throw new LandmarkValidationException("Difficulty must be between 1 and 5.");
        }
    }
}
