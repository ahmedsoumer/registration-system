package com.registration_system.numbering_service.service;

import com.registration_system.numbering_service.dto.NumberGenerationRequest;
import com.registration_system.numbering_service.dto.NumberGenerationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NumberingServiceImplTest {

    private NumberingServiceImpl numberingService;

    @Mock
    private NumberGenerationStrategy firstNameStrategy;

    @Mock
    private NumberGenerationStrategy lastNameStrategy;

    @Mock
    private NumberGenerationStrategy birthdayStrategy;

    @Mock
    private NumberGenerationStrategy counterStrategy;

    private NumberGenerationRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configure les stratégies comme une liste simulée
        List<NumberGenerationStrategy> strategies = Arrays.asList(firstNameStrategy, lastNameStrategy, birthdayStrategy, counterStrategy);
        numberingService = new NumberingServiceImpl(strategies);

        // Configure un NumberGenerationRequest fictif
        request = new NumberGenerationRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setBirthDate("1990-01-01");
        request.setCounter(1);
    }

    @Test
    void testGenerateNumber_WithValidConfigId() {
        // Arrange
        Long configId = 1L;

        when(firstNameStrategy.generate(request, configId)).thenReturn("M-John-");
        when(lastNameStrategy.generate(request, configId)).thenReturn("Doe-");
        when(birthdayStrategy.generate(request, configId)).thenReturn("1990-");
        when(counterStrategy.generate(request, configId)).thenReturn("001");

        // Act
        NumberGenerationResponse response = numberingService.generateNumber(request, configId);

        // Assert
        assertEquals("M-John-Doe-1990-001", response.getGeneratedNumber());
    }

    @Test
    void testGenerateNumber_WithNullConfigId() {
        // Arrange
        Long configId = null;

        when(firstNameStrategy.generate(request, configId)).thenReturn("Default-John-");
        when(lastNameStrategy.generate(request, configId)).thenReturn("Doe-");
        when(birthdayStrategy.generate(request, configId)).thenReturn("1990-");
        when(counterStrategy.generate(request, configId)).thenReturn("001");

        // Act
        NumberGenerationResponse response = numberingService.generateNumber(request, configId);

        // Assert
        assertEquals("Default-John-Doe-1990-001", response.getGeneratedNumber());
    }
}
