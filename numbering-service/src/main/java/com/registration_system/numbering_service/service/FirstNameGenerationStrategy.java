package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.client.ConfigurationClient;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstNameGenerationStrategy implements NumberGenerationStrategy{

    private final ConfigurationClient configurationClient;

    @Autowired
    public FirstNameGenerationStrategy(ConfigurationClient configurationClient) {
        this.configurationClient = configurationClient;
    }


    @Override
    public String generate(NumberGenerationRequest request, Long configId) {
        JsonNode configuration = configurationClient.getConfigurationById(configId);
        StringBuilder result = new StringBuilder();
        String firstname = request.getFirstName();
        int firstNameLength = configuration.path("firstNameLength").asInt(0);
        String firstNamePrefix = configuration.path("firstNamePrefix").asText("");
        String firstNameSuffix = configuration.path("firstNameSuffix").asText("");

        if (firstname != null) {
            String truncatedName = firstNameLength > 0 && firstname.length() > firstNameLength
                    ? firstname.substring(0, firstNameLength)
                    : firstname;

            result.append(firstNamePrefix).append(truncatedName).append(firstNameSuffix);
        }

        return result.toString();
    }
}
