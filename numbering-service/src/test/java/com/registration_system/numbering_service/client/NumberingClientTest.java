package com.registration_system.numbering_service.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.numbering_service.exception.ConfigurationClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfigurationClientTest {

    @Mock
    private RestTemplate restTemplate;

    private ConfigurationClient configurationClient;

    private static final String CONFIG_SERVICE_URL = "http://localhost:8080/api/config/{configId}";
    private static final String DEFAULT_CONFIG_SERVICE_URL = "http://localhost:8080/api/config/default";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestTemplateBuilder restTemplateBuilder = mock(RestTemplateBuilder.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        configurationClient = new ConfigurationClient(CONFIG_SERVICE_URL, DEFAULT_CONFIG_SERVICE_URL, restTemplateBuilder);
    }

    @Test
    void testGetConfigurationById_WithValidConfigId() throws Exception {
        // Arrange
        Long configId = 1L;
        String expectedResponse = "{ \"nameLength\": 3, \"namePrefix\": \"M-\" }";
        when(restTemplate.getForObject(CONFIG_SERVICE_URL.replace("{configId}", configId.toString()), String.class))
                .thenReturn(expectedResponse);

        // Act
        JsonNode result = configurationClient.getConfigurationById(configId);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.get("nameLength").asInt());
        assertEquals("M-", result.get("namePrefix").asText());
    }

    @Test
    void testGetConfigurationById_WithNullConfigId_UsesDefaultConfiguration() throws Exception {
        // Arrange
        String expectedResponse = "{ \"nameLength\": 5, \"namePrefix\": \"Default-\" }";
        when(restTemplate.getForObject(DEFAULT_CONFIG_SERVICE_URL, String.class)).thenReturn(expectedResponse);

        // Act
        JsonNode result = configurationClient.getConfigurationById(null);

        // Assert
        assertNotNull(result);
        assertEquals(5, result.get("nameLength").asInt());
        assertEquals("Default-", result.get("namePrefix").asText());
    }


    @Test
    void testGetConfigurationById_WithRestClientException_ThrowsException() {
        // Arrange
        Long configId = 1L;
        when(restTemplate.getForObject(CONFIG_SERVICE_URL.replace("{configId}", configId.toString()), String.class))
                .thenThrow(new RuntimeException("Rest client exception"));

        // Act & Assert
        ConfigurationClientException exception = assertThrows(ConfigurationClientException.class,
                () -> configurationClient.getConfigurationById(configId));
        assertTrue(exception.getMessage().contains("Échec de la récupération de la configuration"));
    }
}
