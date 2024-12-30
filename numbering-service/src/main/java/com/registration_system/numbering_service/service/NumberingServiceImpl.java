package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import com.registration_system.numbering_service.dto.NumberGenerationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberingServiceImpl implements NumberingService {
    private final List<NumberGenerationStrategy> strategies;

    public NumberingServiceImpl(List<NumberGenerationStrategy> strategies) {
        this.strategies = strategies;
    }


    @Override
    public NumberGenerationResponse generateNumber(NumberGenerationRequest request , Long configId) {
        StringBuilder result = new StringBuilder();
        for (NumberGenerationStrategy strategy : strategies) {
            result.append(strategy.generate(request, configId));
        }
        return new NumberGenerationResponse(result.toString());
    }
}
