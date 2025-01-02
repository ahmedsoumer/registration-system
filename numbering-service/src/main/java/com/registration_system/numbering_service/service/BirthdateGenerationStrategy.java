package com.registration_system.numbering_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.numbering_service.client.ConfigurationClient;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class BirthdateGenerationStrategy implements NumberGenerationStrategy{


    private final ConfigurationClient configurationClient;

    @Autowired
    public BirthdateGenerationStrategy(ConfigurationClient configurationClient) {
        this.configurationClient = configurationClient;
    }


    @Override
    public String generate(NumberGenerationRequest request, Long configId) {
        JsonNode configuration = configurationClient.getConfigurationById(configId);
        StringBuilder result = new StringBuilder();
        String birthdate = request.getBirthDate(); // Format attendu : yyyy-MM-dd
        String birthdateFormat = configuration.path("birthdateFormat").asText("yyyy");
        String birthdatePrefix = configuration.path("birthdatePrefix").asText("");
        String birthdateSuffix = configuration.path("birthdateSuffix").asText("");

        if (birthdate != null) {
            try {
                LocalDate date = LocalDate.parse(birthdate);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(birthdateFormat);

                result.append(birthdatePrefix).append(date.format(formatter)).append(birthdateSuffix);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format: " + birthdate, e);
            }
        }

        return result.toString();
    }
}
