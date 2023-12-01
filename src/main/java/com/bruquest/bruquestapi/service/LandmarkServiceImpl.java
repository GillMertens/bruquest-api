package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkCreateDTO;
import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkDTO;
import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkUpdateDTO;
import com.bruquest.bruquestapi.exception.LandmarkNotFoundException;
import com.bruquest.bruquestapi.model.Landmark;
import com.bruquest.bruquestapi.repository.LandmarkRepository;
import com.bruquest.bruquestapi.util.LandmarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public LandmarkDTO getLandmarkById(Long id) {
        Landmark landmark = landmarkRepository.findById(id)
                .orElseThrow(() -> new LandmarkNotFoundException("Landmark with id: '" + id + "' not found."));
        return LandmarkDTO.toDTO(landmark);
    }

    /**
     * Retrieves a specific landmark by its name.
     *
     * @param name  The name of the landmark to retrieve.
     * @return The landmark with the specified name.
     *
     * @Note: Should have .orElseThrow(() -> new LandmarkNotFoundException("Landmark with name: '" + name + "' not found."));
     */
    @Override
    public LandmarkDTO getLandmarkByName(String name) {
        Landmark landmark;
        try {
            landmark = landmarkRepository.findByName(name);
        } catch (RuntimeException e) {
            throw new LandmarkNotFoundException("Landmark with name: '" + name + "' not found.");
        }
        return LandmarkDTO.toDTO(landmark);
    }

    /**
     * Retrieves all landmarks.
     *
     * @return A list of all landmarks.
     */
    @Override
    public List<LandmarkDTO> getAllLandmarks() {
        return landmarkRepository.findAll().stream()
                .map(LandmarkDTO::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Adds a new landmark.
     *
     * @param landmarkDTO  The landmark to add.
     * @return The added landmark.
     */
    @Override
    public LandmarkDTO addLandmark(LandmarkCreateDTO landmarkDTO) {
        Landmark newLandmark = new Landmark();
        newLandmark.setName(landmarkDTO.name());
        newLandmark.setDescription(landmarkDTO.description());
        newLandmark.setDifficulty(landmarkDTO.difficulty());
        newLandmark.setLatitude(landmarkDTO.latitude());
        newLandmark.setLongitude(landmarkDTO.longitude());
        newLandmark.setImageURL(landmarkDTO.imageURL());
        landmarkValidator.Validate(newLandmark);
        Landmark savedLandmark = landmarkRepository.save(newLandmark);
        return LandmarkDTO.toDTO(savedLandmark);
    }

    /**
     * Updates a specific landmark.
     *
     * @param updateDTO  The landmark with updated information.
     * @return The updated landmark.
     * @throws LandmarkNotFoundException if the landmark to update is not found.
     */
    @Override
    public LandmarkDTO updateLandmark(Long id, LandmarkUpdateDTO updateDTO) {
        Landmark landmarkToUpdate = new Landmark();
        landmarkToUpdate.setName(updateDTO.name());
        landmarkToUpdate.setDescription(updateDTO.description());
        landmarkToUpdate.setDifficulty(updateDTO.difficulty());
        landmarkToUpdate.setLatitude(updateDTO.latitude());
        landmarkToUpdate.setLongitude(updateDTO.longitude());
        landmarkToUpdate.setImageURL(updateDTO.imageURL());
        landmarkValidator.Validate(landmarkToUpdate);
        Landmark foundLandmark = landmarkRepository.findById(id)
                .orElseThrow(() -> new LandmarkNotFoundException("Landmark with id: '" + id + "' not found."));
        Landmark updatedLandmark = landmarkRepository.save(foundLandmark);
        return LandmarkDTO.toDTO(updatedLandmark);
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