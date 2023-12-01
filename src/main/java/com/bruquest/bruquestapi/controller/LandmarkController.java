package com.bruquest.bruquestapi.controller;

import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkCreateDTO;
import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkDTO;
import com.bruquest.bruquestapi.dto.landmarkDTO.LandmarkUpdateDTO;
import com.bruquest.bruquestapi.model.Landmark;
import com.bruquest.bruquestapi.service.LandmarkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landmarks")
public class LandmarkController {

    private LandmarkServiceImpl landmarkService;

    @Autowired
    public LandmarkController(LandmarkServiceImpl landmarkService) {
        this.landmarkService = landmarkService;
    }

    @GetMapping
    public List<LandmarkDTO> getAllLandmarks() {
        return landmarkService.getAllLandmarks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LandmarkDTO> getLandmarkById(@PathVariable Long id) {
        try {
            LandmarkDTO landmark = landmarkService.getLandmarkById(id);
            return ResponseEntity.ok(landmark);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<LandmarkDTO> getLandmarkByName(@PathVariable String name) {
        try {
            LandmarkDTO landmark = landmarkService.getLandmarkByName(name);
            return ResponseEntity.ok(landmark);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<LandmarkDTO> createLandmark(@RequestBody LandmarkCreateDTO landmarkDTO) {
        LandmarkDTO newLandmark = landmarkService.addLandmark(landmarkDTO);
        return ResponseEntity.ok(newLandmark);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandmarkDTO> updateLandmark(@PathVariable Long id, @RequestBody LandmarkUpdateDTO landmarkDTO) {
        try {
            LandmarkDTO updatedLandmark = landmarkService.updateLandmark(id, landmarkDTO);
            return ResponseEntity.ok(updatedLandmark);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
