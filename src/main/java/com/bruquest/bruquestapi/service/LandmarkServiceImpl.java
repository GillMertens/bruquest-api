package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.exception.LandmarkNotFoundException;
import com.bruquest.bruquestapi.model.Landmark;
import com.bruquest.bruquestapi.repository.LandmarkRepository;
import com.bruquest.bruquestapi.util.LandmarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing landmarks.
 * This class provides methods for adding, retrieving, updating and deleting landmarks.
 */
@Service
@Transactional
public class LandmarkServiceImpl implements LandmarkService {
    private final LandmarkRepository landmarkRepository;
    private final LandmarkValidator landmarkValidator;

    /**
     * Constructor for LandmarkServiceImpl.
     *
     * @param landmarkRepository  The repository for accessing landmark data.
     * @param landmarkValidator   The validator for validating landmark data.
     */
    @Autowired
    public LandmarkServiceImpl(LandmarkRepository landmarkRepository, LandmarkValidator landmarkValidator) {
        this.landmarkRepository = landmarkRepository;
        this.landmarkValidator = landmarkValidator;
    }

    /**
     * Retrieves a specific landmark by its ID.
     *
     * @param id  The ID of the landmark to retrieve.
     * @return The landmark with the specified ID.
     * @throws LandmarkNotFoundException if the landmark with the specified ID is not found.
     */
    @Override
    public Landmark getLandmarkById(Long id) {
        return landmarkRepository.findById(id)
                .orElseThrow(() -> new LandmarkNotFoundException("Landmark with id: " + id + " not found."));
    }

    /**
     * Retrieves a specific landmark by its name.
     *
     * @param name  The name of the landmark to retrieve.
     * @return The landmark with the specified name.
     */
    @Override
    public Landmark getLandmarkByName(String name) {
        return landmarkRepository.findByName(name);
    }

    /**
     * Retrieves all landmarks.
     *
     * @return A list of all landmarks.
     */
    @Override
    public List<Landmark> getAllLandmarks() {
        return landmarkRepository.findAll();
    }

    /**
     * Adds a new landmark.
     *
     * @param landmark  The landmark to add.
     * @return The added landmark.
     */
    @Override
    public Landmark addLandmark(Landmark landmark) {
        landmarkValidator.Validate(landmark);
        return landmarkRepository.save(landmark);
    }

    /**
     * Updates a specific landmark.
     *
     * @param landmark  The landmark with updated information.
     * @return The updated landmark.
     * @throws LandmarkNotFoundException if the landmark to update is not found.
     */
    @Override
    public Landmark updateLandmark(Landmark landmark) {
        landmarkValidator.Validate(landmark);
        Landmark foundLandmark = landmarkRepository.findById(landmark.getId())
                .orElseThrow(() -> new LandmarkNotFoundException("Landmark with id: " + landmark.getId() + " not found."));
        return landmarkRepository.save(foundLandmark);
    }

    /**
     * Deletes a specific landmark by its ID.
     *
     * @param id  The ID of the landmark to delete.
     */
    public void deleteLandmark(Long id) {
        landmarkRepository.deleteById(id);
    }
}