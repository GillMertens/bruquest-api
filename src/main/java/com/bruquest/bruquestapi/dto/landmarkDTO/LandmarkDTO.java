package com.bruquest.bruquestapi.dto.landmarkDTO;

import com.bruquest.bruquestapi.model.Landmark;

public record LandmarkDTO(
        Long id,
        String name,
        String description,
        int difficulty,
        double latitude,
        double longitude,
        String imageURL
) {
    public static LandmarkDTO toDTO(Landmark landmark) {
        return new LandmarkDTO(
                landmark.getId(),
                landmark.getName(),
                landmark.getDescription(),
                landmark.getDifficulty(),
                landmark.getLatitude(),
                landmark.getLongitude(),
                landmark.getImageURL()
        );
    }
}
