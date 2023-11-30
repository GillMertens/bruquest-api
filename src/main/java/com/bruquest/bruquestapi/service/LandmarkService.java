package com.bruquest.bruquestapi.service;

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
    Landmark getLandmarkById(Long id);

    /**
     * Retrieves a specific landmark by its name.
     *
     * @param name  The name of the landmark to retrieve.
     * @return The landmark with the specified name.
     */
    Landmark getLandmarkByName(String name);

    /**
     * Retrieves all landmarks.
     *
     * @return A list of all landmarks.
     */
    List<Landmark> getAllLandmarks();

    /**
     * Adds a new landmark.
     *
     * @param landmark  The landmark to add.
     * @return The added landmark.
     */
    Landmark addLandmark(Landmark landmark);

    /**
     * Updates a specific landmark.
     *
     * @param landmark  The landmark with updated information.
     * @return The updated landmark.
     */
    Landmark updateLandmark(Landmark landmark);

    /**
     * Deletes a specific landmark by its ID.
     *
     * @param id  The ID of the landmark to delete.
     */
    void deleteLandmark(Long id);
}