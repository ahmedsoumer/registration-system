package com.registration_system.numbering_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import com.registration_system.numbering_service.dto.NumberGenerationResponse;
import com.registration_system.numbering_service.service.NumberingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NumberGenerationController.class)
class NumberGenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private NumberingServiceImpl numberingService;

    @Autowired
    private ObjectMapper objectMapper;

    private NumberGenerationRequest request;

    @BeforeEach
    void setUp() {
        request = new NumberGenerationRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate("1990-01-01");
        request.setCounter(1);
    }

    @Test
    void testGenerateNumber_WithValidConfigId() throws Exception {
        // Arrange
        Long configId = 1L;
        NumberGenerationResponse expectedResponse = new NumberGenerationResponse("M-John-Doe-1990-001");

        when(numberingService.generateNumber(request, configId)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/api/number")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("configId", configId.toString())
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.generatedNumber").value("M-John-Doe-1990-001"));
    }

    @Test
    void testGenerateNumber_WithNullConfigId() throws Exception {
        // Arrange
        Long configId = null;
        NumberGenerationResponse expectedResponse = new NumberGenerationResponse("Default-John-Doe-1990-001");

        when(numberingService.generateNumber(request, configId)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(post("/api/number")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.generatedNumber").value("Default-John-Doe-1990-001"));
    }

    @Test
    void testGenerateNumber_WithInvalidRequest() throws Exception {
        // Arrange
        NumberGenerationRequest invalidRequest = new NumberGenerationRequest(); // Empty request

        // Act & Assert
        mockMvc.perform(post("/api/number")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }
}