package com.bruquest.bruquestapi.dto.landmarkDTO;


import com.bruquest.bruquestapi.model.Landmark;

public record LandmarkCreateDTO(
        String name,
        String description,
        int difficulty,
        double latitude,
        double longitude,
        String imageURL
) {
}
