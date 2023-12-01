package com.bruquest.bruquestapi.controller;

import com.bruquest.bruquestapi.dto.DiscoveryDTO.DiscoveryCreateDTO;
import com.bruquest.bruquestapi.dto.DiscoveryDTO.DiscoveryDTO;
import com.bruquest.bruquestapi.service.DiscoveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discoveries")
public class DiscoveryController {
    private final DiscoveryService discoveryService;

    public DiscoveryController(DiscoveryService discoveryService) {
        this.discoveryService = discoveryService;
    }

    @PostMapping
    public ResponseEntity<DiscoveryDTO> createDiscovery(@RequestBody DiscoveryCreateDTO discoveryCreateDTO) {
        DiscoveryDTO discoveryDTO = discoveryService.createDiscovery(discoveryCreateDTO);
        return ResponseEntity.ok(discoveryDTO);
    }

    // Other methods for reading, updating, and deleting discoveries...
}