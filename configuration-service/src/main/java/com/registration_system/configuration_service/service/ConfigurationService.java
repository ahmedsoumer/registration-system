package com.registration_system.configuration_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;

public interface ConfigurationService {
    ConfigurationResponse saveConfiguration(ConfigurationRequest request);
    JsonNode getConfigurationById(Long configId);
    JsonNode getDefaultConfiguration();
}
