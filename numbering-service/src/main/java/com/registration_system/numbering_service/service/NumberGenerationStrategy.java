package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;

public interface NumberGenerationStrategy {
    String generate(NumberGenerationRequest request, Long configId);
}
