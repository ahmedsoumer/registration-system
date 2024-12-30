package com.registration_system.configuration_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.configuration_service.config.DefaultConfigurationProperties;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import com.registration_system.configuration_service.entity.Configuration;
import com.registration_system.configuration_service.exception.ConfigurationNotFoundException;
import org.springframework.stereotype.Service;
import com.registration_system.configuration_service.repository.ConfigurationRepository;

import java.util.Optional;

@Service
public class ConfigurationServiceImpl implements ConfigurationService{

    private final ConfigurationRepository configurationRepository;
    private final DefaultConfigurationProperties configurationProperties;


    public ConfigurationServiceImpl(ConfigurationRepository configurationRepository , DefaultConfigurationProperties configurationProperties) {
        this.configurationRepository = configurationRepository;
        this.configurationProperties = configurationProperties;

    }

    @Override
    public ConfigurationResponse saveConfiguration(ConfigurationRequest request) {
        Configuration configuration = new Configuration();
        configuration.setLastNameLength(request.getLastNameLength());
        configuration.setLastNamePrefix(request.getLastNamePrefix());
        configuration.setLastNameSuffix(request.getLastNameSuffix());
        configuration.setFirstNameLength(request.getFirstNameLength());
        configuration.setFirstNamePrefix(request.getFirstNamePrefix());
        configuration.setFirstNameSuffix(request.getFirstNameSuffix());
        configuration.setBirthDateFormat(request.getBirthDateFormat());
        configuration.setBirthDatePrefix(request.getBirthDatePrefix());
        configuration.setBirthDateSuffix(request.getBirthDateSuffix());
        configuration.setCounterFormat(request.getCounterFormat());

        Configuration savedConfiguration = configurationRepository.save(configuration);
        return mapToResponse(savedConfiguration);
    }

    private ConfigurationResponse mapToResponse(Configuration configuration) {
        return new ConfigurationResponse(
                configuration.getLastNameLength(),
                configuration.getLastNamePrefix(),
                configuration.getLastNameSuffix(),
                configuration.getFirstNameLength(),
                configuration.getFirstNamePrefix(),
                configuration.getFirstNameSuffix(),
                configuration.getBirthDateFormat(),
                configuration.getBirthDatePrefix(),
                configuration.getBirthDateSuffix(),
                configuration.getCounterFormat(),
                configuration.getId()
        );
    }

    public JsonNode getConfigurationById(Long configId) {
        Optional<Configuration> configurationOptional = configurationRepository.findById(configId);

        if (configurationOptional.isPresent()) {
            Configuration configuration = configurationOptional.get();
            // Convertir l'objet Configuration en JsonNode ou renvoyer un DTO si nécessaire
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.valueToTree(configuration);
        } else {
            throw new ConfigurationNotFoundException("Configuration not found for id: " + configId);

        }
    }

    public JsonNode getDefaultConfiguration() {
        // Retourner la configuration par défaut ici, soit à partir de la DB soit via une configuration codée en dur
        return new ObjectMapper().createObjectNode()
                .put("lastNameLength", configurationProperties.getLastNameLength())
                .put("lastNamePrefix", configurationProperties.getLastNamePrefix())
                .put("lastNameSuffix", configurationProperties.getLastNameSuffix())
                .put("firstNameLength", configurationProperties.getFirstNameLength())
                .put("firstNamePrefix", configurationProperties.getFirstNamePrefix())
                .put("firstNameSuffix", configurationProperties.getFirstNameSuffix())
                .put("birthDateFormat", configurationProperties.getBirthDateFormat())
                .put("birthDatePrefix", configurationProperties.getBirthDatePrefix())
                .put("birthDateSuffix", configurationProperties.getBirthDateSuffix())
                .put("counterFormat", configurationProperties.getCounterFormat());
    }
}
