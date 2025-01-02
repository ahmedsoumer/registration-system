package com.registration_system.configuration_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import com.registration_system.configuration_service.service.ConfigurationService;
import com.registration_system.configuration_service.service.ConfigurationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ConfigurationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ConfigurationServiceImpl configurationServiceImpl;

    @InjectMocks
    private ConfigurationController configurationController;

    private ConfigurationRequest request;
    private ConfigurationResponse response;
    private JsonNode jsonNode;

    @BeforeEach
    void setUp() throws Exception {
        // Initialize mocks and MockMvc
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(configurationController).build();

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

        response = new ConfigurationResponse(
                5, "M-", "-X", 3, "P-", "-Y", "YYYY", "BD-", "-Z", "%03d", 1L
        );

        // Initialize mock JSON response
        ObjectMapper objectMapper = new ObjectMapper();
        String json = """
                {
                                "lastNameLength": 5,
                                "lastNamePrefix": "M-",
                                "lastNameSuffix": "-X",
                                "firstNameLength": 3,
                                "firstNamePrefix": "P-",
                                "firstNameSuffix": "-Y",
                                "birthDateFormat": "YYYY",
                                "birthDatePrefix": "BD-",
                                "birthDateSuffix": "-Z",
                                "counterFormat": "%03d"
                }
                """;
        jsonNode = objectMapper.readTree(json);
    }

    @Test
    void testSaveConfiguration() throws Exception {
        // Mock the behavior of saveConfiguration
        when(configurationServiceImpl.saveConfiguration(request)).thenReturn(response);

        // Perform POST request and verify the response
        mockMvc.perform(post("/api/config")
                        .contentType("application/json")
                        .content("""
                        {
                            "lastNameLength": 5,
                            "lastNamePrefix": "M-",
                            "lastNameSuffix": "-X",
                            "firstNameLength": 3,
                            "firstNamePrefix": "P-",
                            "firstNameSuffix": "-Y",
                            "birthDateFormat": "YYYY",
                            "birthDatePrefix": "BD-",
                            "birthDateSuffix": "-Z",
                            "counterFormat": "%03d"
                        }
                        """))
                .andExpect(status().isOk());
    }

    @Test
    void testGetConfigurationById() throws Exception {
        Long configId = 1L;

        // Mock the behavior of getConfigurationById
        when(configurationServiceImpl.getConfigurationById(configId)).thenReturn(jsonNode);

        // Perform GET request and verify the response
        mockMvc.perform(get("/api/config/{configId}", configId))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "firstNamePrefix": "P-",
                            "firstNameLength": 3
                        }
                        """));
    }

    @Test
    void testGetDefaultConfiguration() throws Exception {
        // Mock the behavior of getDefaultConfiguration
        when(configurationServiceImpl.getDefaultConfiguration()).thenReturn(jsonNode);

        // Perform GET request and verify the response
        mockMvc.perform(get("/api/config/default"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "firstNamePrefix": "P-",
                            "firstNameLength": 3
                        }
                        """));
    }
}
