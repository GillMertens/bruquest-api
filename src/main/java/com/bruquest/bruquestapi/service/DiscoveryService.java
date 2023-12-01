package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.dto.DiscoveryDTO.DiscoveryCreateDTO;
import com.bruquest.bruquestapi.dto.DiscoveryDTO.DiscoveryDTO;
import com.bruquest.bruquestapi.model.Discovery;
import com.bruquest.bruquestapi.model.Landmark;
import com.bruquest.bruquestapi.model.User;

import java.util.List;

/**
 * Interface for the service layer of the Discovery entity.
 * This interface provides methods for CRUD operations on discoveries.
 */
public interface DiscoveryService {

    /**
     * Adds a new discovery.
     *
     * @param discovery  The discovery to add.
     * @return The added discovery.
     */
    DiscoveryDTO createDiscovery(DiscoveryCreateDTO discoveryCreateDTO);

    /**
     * Retrieves all discoveries by a specific user.
     *
     * @param user  The user whose discoveries to retrieve.
     * @return A list of discoveries by the user.
     */
    List<Discovery> getDiscoveriesByUser(User user);

    /**
     * Retrieves all discoveries by a specific user at a specific landmark.
     *
     * @param user      The user whose discoveries to retrieve.
     * @param landmark  The landmark where the discoveries were made.
     * @return A list of discoveries by the user at the landmark.
     */
    List<Discovery> getDiscoveriesByUserAndLandmark(User user, Landmark landmark);

    /**
     * Retrieves all discoveries at a specific landmark.
     *
     * @param landmark  The landmark where the discoveries were made.
     * @return A list of discoveries at the landmark.
     */
    List<Discovery> getDiscoveriesByLandmark(Landmark landmark);

    /**
     * Retrieves all discoveries.
     *
     * @return A list of all discoveries.
     */
    List<Discovery> getAllDiscoveries();

    /**
     * Retrieves a specific discovery by its ID.
     *
     * @param id  The ID of the discovery to retrieve.
     * @return The discovery with the specified ID.
     */
    Discovery getDiscoveryById(Long id);

    /**
     * Updates a specific discovery.
     *
     * @param discovery  The discovery with updated information.
     * @return The updated discovery.
     */
    Discovery updateDiscovery(Discovery discovery);

    /**
     * Deletes a specific discovery by its ID.
     *
     * @param id  The ID of the discovery to delete.
     */
    void deleteDiscovery(Long id);
}