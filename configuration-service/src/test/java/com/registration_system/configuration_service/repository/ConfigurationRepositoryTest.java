package com.registration_system.configuration_service.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import com.registration_system.configuration_service.exception.ConfigurationNotFoundException;
import com.registration_system.configuration_service.service.ConfigurationService;
import com.registration_system.configuration_service.service.ConfigurationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConfigurationRepositoryTest {
    @InjectMocks
    private ConfigurationServiceImpl configurationService;

    @Mock
    private ConfigurationService configurationServiceMock;

    private ConfigurationRequest request;

    @BeforeEach
    void setUp() {
        // Initialize test data
        request = new ConfigurationRequest();
        request.setNameLength(5);
        request.setNamePrefix("M-");
        request.setLastNameSuffix("-X");
        request.setFirstNameLength(3);
        request.setFirstNamePrefix("P-");
        request.setFirstNameSuffix("-Y");
        request.setBirthDateFormat("YYYY");
        request.setBirthDatePrefix("BD-");
        request.setBirthDateSuffix("-Z");
        request.setCounterFormat("%03d");
    }

    @Test
    void testSaveConfiguration() {
        // Mocking the behavior of saveConfiguration
        ConfigurationResponse expectedResponse = new ConfigurationResponse(
                5, "M-", "-X", 3, "P-", "-Y", "YYYY", "BD-", "-Z", "%03d", 1L
        );

        when(configurationServiceMock.saveConfiguration(request)).thenReturn(expectedResponse);

        // Act
        ConfigurationResponse actualResponse = configurationServiceMock.saveConfiguration(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getLastNameLength(), actualResponse.getLastNameLength());
        assertEquals(expectedResponse.getLastNamePrefix(), actualResponse.getLastNamePrefix());
        assertEquals(expectedResponse.getLastNameSuffix(), actualResponse.getLastNameSuffix());
    }

    @Test
    void testGetConfigurationById_Found() {
        // Mocking the behavior of getConfigurationById
        Long configId = 1L;
        JsonNode expectedJson = mock(JsonNode.class);

        when(configurationServiceMock.getConfigurationById(configId)).thenReturn(expectedJson);

        // Act
        JsonNode actualJson = configurationServiceMock.getConfigurationById(configId);

        // Assert
        assertNotNull(actualJson);
        assertEquals(expectedJson, actualJson);
    }

    @Test
    void testGetConfigurationById_NotFound() {
        // Mocking the behavior of getConfigurationById when no configuration is found
        Long configId = 1L;

        when(configurationServiceMock.getConfigurationById(configId))
                .thenThrow(new ConfigurationNotFoundException("Configuration not found"));

        // Act & Assert
        assertThrows(ConfigurationNotFoundException.class, () -> configurationServiceMock.getConfigurationById(configId));
    }

    @Test
    void testGetDefaultConfiguration() {
        // Mocking the behavior of getDefaultConfiguration
        JsonNode expectedJson = mock(JsonNode.class);

        when(configurationServiceMock.getDefaultConfiguration()).thenReturn(expectedJson);

        // Act
        JsonNode actualJson = configurationServiceMock.getDefaultConfiguration();

        // Assert
        assertNotNull(actualJson);
        assertEquals(expectedJson, actualJson);
    }
}
