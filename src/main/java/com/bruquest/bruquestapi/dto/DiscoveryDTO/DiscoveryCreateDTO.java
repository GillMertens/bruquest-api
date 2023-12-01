package com.bruquest.bruquestapi.dto.DiscoveryDTO;

public record DiscoveryCreateDTO(
        Long userId,
        Long landmarkId,
        String imageURL
) {
}
