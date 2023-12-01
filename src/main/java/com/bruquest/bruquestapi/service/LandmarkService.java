package com.bruquest.bruquestapi.service;

import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkCreateDTO;
import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkDTO;
import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkUpdateDTO;
import com.bruquest.bruquestapi.model.Landmark;

import java.util.List;

/**
 * Interface for the service layer of the Landmark entity.
 * This interface provides methods for CRUD operations on landmarks.
 */
public interface LandmarkService {

    /**
     * Retrieves a specific landmark by its ID.
     *
     * @param id  The ID of the landmark to retrieve.
     * @return The landmark with the specified ID.
     */
    LandmarkDTO getLandmarkById(Long id);

    /**
     * Retrieves a specific landmark by its name.
     *
     * @param name  The name of the landmark to retrieve.
     * @return The landmark with the specified name.
     */
    LandmarkDTO getLandmarkByName(String name);

    /**
     * Retrieves all landmarks.
     *
     * @return A list of all landmarks.
     */
    List<LandmarkDTO> getAllLandmarks();

    /**
     * Adds a new landmark.
     *
     * @param landmark  The landmark to add.
     * @return The added landmark.
     */
    LandmarkDTO addLandmark(LandmarkCreateDTO landmarkDTO);

    /**
     * Updates a specific landmark.
     *
     * @param landmarkDTO  The landmark with updated information.
     * @return The updated landmark.
     */
    LandmarkDTO updateLandmark(Long id, LandmarkUpdateDTO landmarkDTO);

    /**
     * Deletes a specific landmark by its ID.
     *
     * @param id  The ID of the landmark to delete.
     */
    void deleteLandmark(Long id);
}