package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.client.ConfigurationClient;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CounterGenerationStrategy implements NumberGenerationStrategy{

    private final ConfigurationClient configurationClient;

    @Autowired
    public CounterGenerationStrategy(ConfigurationClient configurationClient) {
        this.configurationClient = configurationClient;
    }


    @Override
    public String generate(NumberGenerationRequest request, Long configId) {
        JsonNode configuration = configurationClient.getConfigurationById(configId);
        int counter = request.getCounter();
        int initialCounterValue = configuration.path("counterInitialValue").asInt(0);
        String counterFormat = configuration.path("counterFormat").asText("%04d"); // Format par défaut

        counter += initialCounterValue;

        return String.format(counterFormat, counter);
    }
}
