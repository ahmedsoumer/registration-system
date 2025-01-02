package com.registration_system.numbering_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import com.registration_system.numbering_service.dto.NumberGenerationResponse;
import com.registration_system.numbering_service.service.NumberingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest(NumberGenerationController.class)
class NumberGenerationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @TestConfiguration
    static class TestConfig {
        @Bean
        public NumberingService numberingService() {
            return mock(NumberingService.class); // Use Mockito or similar
        }
    }

    @Autowired
    private NumberingService numberingService;

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

    }
}
