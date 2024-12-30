package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import com.registration_system.numbering_service.dto.NumberGenerationResponse;

public interface NumberingService {
    NumberGenerationResponse generateNumber(NumberGenerationRequest request , Long configId);
}
