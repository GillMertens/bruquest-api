package com.bruquest.bruquestapi.repository;

import com.bruquest.bruquestapi.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
    Landmark findByName(String name);
}
