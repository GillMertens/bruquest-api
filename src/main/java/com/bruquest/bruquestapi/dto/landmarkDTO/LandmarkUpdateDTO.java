package com.bruquest.bruquestapi.dto.landmarkDTO;

public record LandmarkUpdateDTO(
        String name,
        String description,
        int difficulty,
        double latitude,
        double longitude,
        String imageURL
) {
}
