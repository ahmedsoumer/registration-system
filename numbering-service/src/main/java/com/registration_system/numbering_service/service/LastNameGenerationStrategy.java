package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.client.ConfigurationClient;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LastNameGenerationStrategy implements NumberGenerationStrategy{

    private final ConfigurationClient configurationClient;

    @Autowired
    public LastNameGenerationStrategy(ConfigurationClient configurationClient) {
        this.configurationClient = configurationClient;
    }

    @Override
    public String generate(NumberGenerationRequest request, Long configId) {
        JsonNode configuration = configurationClient.getConfigurationById(configId);
        StringBuilder result = new StringBuilder();
        String lastname = request.getLastName();
        int lastNameLength = configuration.path("lastNameLength").asInt(0);
        String lastNamePrefix = configuration.path("lastNamePrefix").asText("");
        String lastNameSuffix = configuration.path("lastNameSuffix").asText("");

        if (lastname != null) {
            String truncatedSurname = lastNameLength > 0 && lastname.length() > lastNameLength
                    ? lastname.substring(0, lastNameLength)
                    : lastname;

            result.append(lastNamePrefix).append(truncatedSurname).append(lastNameSuffix);
        }

        return result.toString();
    }
}
