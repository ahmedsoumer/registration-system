package com.registration_system.numbering_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import com.registration_system.numbering_service.dto.NumberGenerationResponse;
import com.registration_system.numbering_service.service.NumberingService;
import com.registration_system.numbering_service.service.NumberingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/number")
public class NumberGenerationController {
    private final NumberingService numberGenerationService;

    @Autowired
    public NumberGenerationController(NumberingService numberGenerationService) {
        this.numberGenerationService = numberGenerationService;
    }

    @PostMapping
    public ResponseEntity<NumberGenerationResponse> generateNumber(
            @RequestBody NumberGenerationRequest request,
            @RequestParam(required = false) Long configId) {

        NumberGenerationResponse response = numberGenerationService.generateNumber(request, configId);
        return ResponseEntity.ok(response);
    }
}
