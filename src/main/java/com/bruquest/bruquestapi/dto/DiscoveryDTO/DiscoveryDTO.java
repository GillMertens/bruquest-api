package com.bruquest.bruquestapi.dto.DiscoveryDTO;

import com.bruquest.bruquestapi.model.Discovery;

public record DiscoveryDTO(
        Long id,
        Long userId,
        Long landmarkId,
        String imageURL
) {
    public static DiscoveryDTO toDTO(Discovery discovery) {
        return new DiscoveryDTO(
                discovery.getId(),
                discovery.getUser().getId(),
                discovery.getLandmark().getId(),
                discovery.getImageURL()
        );
    }
}
