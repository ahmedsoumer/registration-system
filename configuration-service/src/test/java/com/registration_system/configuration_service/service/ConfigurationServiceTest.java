package com.registration_system.configuration_service.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.configuration_service.config.DefaultConfigurationProperties;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import com.registration_system.configuration_service.entity.Configuration;
import com.registration_system.configuration_service.exception.ConfigurationNotFoundException;
import com.registration_system.configuration_service.repository.ConfigurationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigurationServiceImplTest {

    @InjectMocks
    private ConfigurationServiceImpl configurationService;

    @Mock
    private DefaultConfigurationProperties configurationProperties;

    @Mock
    private ConfigurationRepository configurationRepository;

    private ConfigurationRequest request;
    private Configuration configuration;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Initialize test data
        request = new ConfigurationRequest();
        request.setLastNameLength(5);
        request.setLastNamePrefix("M-");
        request.setLastNameSuffix("-X");
        request.setFirstNameLength(3);
        request.setFirstNamePrefix("P-");
        request.setFirstNameSuffix("-Y");
        request.setBirthDateFormat("YYYY");
        request.setBirthDatePrefix("BD-");
        request.setBirthDateSuffix("-Z");
        request.setCounterFormat("%03d");

        configuration = new Configuration();
        configuration.setId(1L);
        configuration.setLastNameLength(5);
        configuration.setLastNamePrefix("M-");
        configuration.setLastNameSuffix("-X");
        configuration.setFirstNameLength(3);
        configuration.setFirstNamePrefix("P-");
        configuration.setFirstNameSuffix("-Y");
        configuration.setBirthDateFormat("YYYY");
        configuration.setBirthDatePrefix("BD-");
        configuration.setBirthDateSuffix("-Z");
        configuration.setCounterFormat("%03d");
    }

    @Test
    void testSaveConfiguration() {
        // Arrange
        when(configurationRepository.save(any(Configuration.class))).thenReturn(configuration);

        // Act
        ConfigurationResponse response = configurationService.saveConfiguration(request);

        // Assert
        assertNotNull(response);
        assertEquals(5, response.getLastNameLength());
        assertEquals("M-", response.getLastNamePrefix());
        assertEquals("-X", response.getLastNameSuffix());
    }

    @Test
    void testGetConfigurationById_Found() {
        // Arrange
        when(configurationRepository.findById(1L)).thenReturn(Optional.of(configuration));

        // Act
        JsonNode result = configurationService.getConfigurationById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("M-", result.get("lastNamePrefix").asText());
        assertEquals("5", result.get("lastNameLength").asText());
    }

    @Test
    void testGetConfigurationById_NotFound() {
        // Arrange
        when(configurationRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ConfigurationNotFoundException.class, () -> configurationService.getConfigurationById(1L));
    }

    @Test
    void testGetDefaultConfiguration() {

        // Arrange
        when(configurationProperties.getFirstNamePrefix()).thenReturn("M-");
        when(configurationProperties.getFirstNameLength()).thenReturn(3);
        when(configurationProperties.getLastNamePrefix()).thenReturn("P-");
        when(configurationProperties.getLastNameLength()).thenReturn(2);

        // Act
        JsonNode result = configurationService.getDefaultConfiguration();

        // Assert
        assertNotNull(result);
        assertEquals("M-", result.get("firstNamePrefix").asText());
        assertEquals("3", result.get("firstNameLength").asText());
        assertEquals("P-", result.get("lastNamePrefix").asText());
        assertEquals("2", result.get("lastNameLength").asText());
    }
}

