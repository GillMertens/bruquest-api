package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.dto.DiscoveryDTO.DiscoveryCreateDTO;
import com.bruquest.bruquestapi.dto.DiscoveryDTO.DiscoveryDTO;
import com.bruquest.bruquestapi.exception.DiscoveryNotFoundException;
import com.bruquest.bruquestapi.exception.LandmarkNotFoundException;
import com.bruquest.bruquestapi.exception.UserNotFoundException;
import com.bruquest.bruquestapi.model.Discovery;
import com.bruquest.bruquestapi.model.Landmark;
import com.bruquest.bruquestapi.model.User;
import com.bruquest.bruquestapi.repository.DiscoveryRepository;
import com.bruquest.bruquestapi.repository.LandmarkRepository;
import com.bruquest.bruquestapi.repository.UserRepository;
import com.bruquest.bruquestapi.util.DiscoveryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing discoveries.
 * This class provides methods for adding, retrieving, updating and deleting discoveries.
 */
@Service
@Transactional
public class DiscoveryServiceImpl implements DiscoveryService {
    private final UserRepository userRepository;
    private final LandmarkRepository landmarkRepository;
    private final DiscoveryRepository discoveryRepository;
    private final DiscoveryValidator discoveryValidator;

    /**
     * Constructor for DiscoveryServiceImpl.
     *
     * @param discoveryRepository  The repository for accessing discovery data.
     * @param discoveryValidator   The validator for validating discovery data.
     */
    @Autowired
    public DiscoveryServiceImpl(DiscoveryRepository discoveryRepository, DiscoveryValidator discoveryValidator, UserRepository userRepository, LandmarkRepository landmarkRepository) {
        this.discoveryRepository = discoveryRepository;
        this.discoveryValidator = discoveryValidator;
        this.userRepository = userRepository;
        this.landmarkRepository = landmarkRepository;
    }

    /**
     * Adds a new discovery.
     *
     * @param discovery  The discovery to add.
     * @return The added discovery.
     */
    @Override
    public DiscoveryDTO createDiscovery(DiscoveryCreateDTO discoveryCreateDTO) {
        User user = userRepository.findById(discoveryCreateDTO.userId())
                .orElseThrow(() -> new UserNotFoundException("Could not find user with id: " + discoveryCreateDTO.userId(), HttpStatus.NOT_FOUND));
        Landmark landmark = landmarkRepository.findById(discoveryCreateDTO.landmarkId())
                .orElseThrow(() -> new LandmarkNotFoundException("Could not find landmark with id: " + discoveryCreateDTO.landmarkId()));

        Discovery discovery = new Discovery();
        discovery.setUser(user);
        discovery.setLandmark(landmark);
        discovery.setImageURL(discoveryCreateDTO.imageURL());
        discovery.setDate(LocalDateTime.now());
        Discovery savedDiscovery = discoveryRepository.save(discovery);

        return DiscoveryDTO.toDTO(savedDiscovery);
    }

    /**
     * Retrieves all discoveries by a specific user.
     *
     * @param user  The user whose discoveries to retrieve.
     * @return A list of discoveries by the user.
     */
    @Override
    public List<Discovery> getDiscoveriesByUser(User user) {
        return discoveryRepository.findByUser(user);
    }

    /**
     * Retrieves all discoveries by a specific user at a specific landmark.
     *
     * @param user      The user whose discoveries to retrieve.
     * @param landmark  The landmark where the discoveries were made.
     * @return A list of discoveries by the user at the landmark.
     */
    @Override
    public List<Discovery> getDiscoveriesByUserAndLandmark(User user, Landmark landmark) {
        return discoveryRepository.findByUserAndLandmark(user, landmark);
    }

    /**
     * Retrieves all discoveries at a specific landmark.
     *
     * @param landmark  The landmark where the discoveries were made.
     * @return A list of discoveries at the landmark.
     */
    @Override
    public List<Discovery> getDiscoveriesByLandmark(Landmark landmark) {
        return discoveryRepository.findByLandmark(landmark);
    }

    /**
     * Retrieves all discoveries.
     *
     * @return A list of all discoveries.
     */
    @Override
    public List<Discovery> getAllDiscoveries() {
        return discoveryRepository.findAll();
    }

    /**
     * Retrieves a specific discovery by its ID.
     *
     * @param id  The ID of the discovery to retrieve.
     * @return The discovery with the specified ID.
     * @throws DiscoveryNotFoundException if the discovery with the specified ID is not found.
     */
    @Override
    public Discovery getDiscoveryById(Long id) {
        return discoveryRepository.findById(id)
                .orElseThrow(() -> new DiscoveryNotFoundException("Discovery not found with id: " + id));
    }

    /**
     * Updates a specific discovery.
     *
     * @param discovery  The discovery with updated information.
     * @return The updated discovery.
     * @throws DiscoveryNotFoundException if the discovery to update is not found.
     */
    @Override
    public Discovery updateDiscovery(Discovery discovery) {
        Discovery existingDiscovery = discoveryRepository.findById(discovery.getId())
                .orElseThrow(() -> new DiscoveryNotFoundException("Discovery not found with id: " + discovery.getId()));

        if (discovery.getUser() != null) {
            existingDiscovery.setUser(discovery.getUser());
        }
        if (discovery.getLandmark() != null) {
            existingDiscovery.setLandmark(discovery.getLandmark());
        }
        if (discovery.getDate() != null) {
            existingDiscovery.setDate(discovery.getDate());
        }
        if (discovery.getImageURL() != null) {
            existingDiscovery.setImageURL(discovery.getImageURL());
        }

        return discoveryRepository.save(existingDiscovery);
    }

    /**
     * Deletes a specific discovery by its ID.
     *
     * @param id  The ID of the discovery to delete.
     */
    @Override
    public void deleteDiscovery(Long id) {
        discoveryRepository.deleteById(id);
    }
}