package com.bruquest.bruquestapi.repository;

import com.bruquest.bruquestapi.model.Discovery;
import com.bruquest.bruquestapi.model.Landmark;
import com.bruquest.bruquestapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscoveryRepository extends JpaRepository<Discovery, Long> {
    List<Discovery> findByUser(User user);
    List<Discovery> findByLandmark(Landmark landmark);
    List<Discovery> findByUserAndLandmark(User user, Landmark landmark);
}
