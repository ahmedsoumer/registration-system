package com.registration_system.configuration_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import com.registration_system.configuration_service.service.ConfigurationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ConfigurationControllerTest {


    private MockMvc mockMvc;

    @Mock
    private ConfigurationService configurationService;

    @InjectMocks
    private ConfigurationController configurationController;

    private ConfigurationRequest request;
    private ConfigurationResponse response;
    private JsonNode jsonNode;

    @BeforeEach
    void setUp() {
        // Initialisation de MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(configurationController).build();

        // Initialisation des données de test
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

        response = new ConfigurationResponse(
                5, "M-", "-X", 3, "P-", "-Y", "YYYY", "BD-", "-Z", "%03d", 1L
        );

        jsonNode = mock(JsonNode.class);
    }

    @Test
    void testSaveConfiguration() throws Exception {
        // Mocking the behavior of saveConfiguration
        when(configurationService.saveConfiguration(request)).thenReturn(response);

        // Perform POST request and verify the response
        mockMvc.perform(post("/api/config")
                        .contentType("application/json")
                        .content("{ \"nameLength\": 5, \"namePrefix\": \"M-\", \"nameSuffix\": \"-X\", " +
                                "\"firstNameLength\": 3, \"firstNamePrefix\": \"P-\", \"firstNameSuffix\": \"-Y\", " +
                                "\"birthDateFormat\": \"YYYY\", \"birthDatePrefix\": \"BD-\", \"birthDateSuffix\": \"-Z\", " +
                                "\"counterFormat\": \"%03d\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameLength").value(5))
                .andExpect(jsonPath("$.namePrefix").value("M-"))
                .andExpect(jsonPath("$.nameSuffix").value("-X"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetConfigurationById() throws Exception {
        Long configId = 1L;

        // Mocking the behavior of getConfigurationById
        when(configurationService.getConfigurationById(configId)).thenReturn(jsonNode);

        // Perform GET request and verify the response
        mockMvc.perform(get("/api/config/{configId}", configId))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));  // Expected response would be JSON, use {} as placeholder
    }

    @Test
    void testGetDefaultConfiguration() throws Exception {
        // Mocking the behavior of getDefaultConfiguration
        when(configurationService.getDefaultConfiguration()).thenReturn(jsonNode);

        // Perform GET request and verify the response
        mockMvc.perform(get("/api/config/default"))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));  // Expected response would be JSON, use {} as placeholder
    }

    @Test
    void testSaveConfiguration_InvalidData() throws Exception {
        // Simulate invalid input by omitting a required field or sending bad data

        // Perform POST request with invalid data and verify the response status (Bad Request)
        mockMvc.perform(post("/api/config")
                        .contentType("application/json")
                        .content("{ \"nameLength\": \"invalid\" }"))  // Invalid data type
                .andExpect(status().isBadRequest());
    }

}
