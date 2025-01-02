package com.registration_system.configuration_service.repository;

import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import com.registration_system.configuration_service.entity.Configuration;
import com.registration_system.configuration_service.service.ConfigurationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ConfigurationServiceTest {

    @InjectMocks
    private ConfigurationServiceImpl configurationServiceImpl;

    @Mock
    private ConfigurationRepository configurationRepository;

    private ConfigurationRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize request DTO
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
    }

    @Test
    void testSaveConfiguration() {
        // Mock the entity to be returned by the repository
        Configuration savedEntity = new Configuration();
        savedEntity.setId(1L);
        savedEntity.setLastNameLength(request.getLastNameLength());
        savedEntity.setLastNamePrefix(request.getLastNamePrefix());
        savedEntity.setLastNameSuffix(request.getLastNameSuffix());
        savedEntity.setFirstNameLength(request.getFirstNameLength());
        savedEntity.setFirstNamePrefix(request.getFirstNamePrefix());
        savedEntity.setFirstNameSuffix(request.getFirstNameSuffix());
        savedEntity.setBirthDateFormat(request.getBirthDateFormat());
        savedEntity.setBirthDatePrefix(request.getBirthDatePrefix());
        savedEntity.setBirthDateSuffix(request.getBirthDateSuffix());
        savedEntity.setCounterFormat(request.getCounterFormat());

        // Mock repository save method
        when(configurationRepository.save(any(Configuration.class))).thenReturn(savedEntity);

        // Call service method
        ConfigurationResponse response = configurationServiceImpl.saveConfiguration(request);

        // Verify response
        assertNotNull(response);
        assertEquals(savedEntity.getId(), response.getId());
        assertEquals(savedEntity.getLastNameLength(), response.getLastNameLength());
        assertEquals(savedEntity.getLastNamePrefix(), response.getLastNamePrefix());
        assertEquals(savedEntity.getLastNameSuffix(), response.getLastNameSuffix());
        assertEquals(savedEntity.getFirstNameLength(), response.getFirstNameLength());
        assertEquals(savedEntity.getFirstNamePrefix(), response.getFirstNamePrefix());
        assertEquals(savedEntity.getFirstNameSuffix(), response.getFirstNameSuffix());
        assertEquals(savedEntity.getBirthDateFormat(), response.getBirthDateFormat());
        assertEquals(savedEntity.getBirthDatePrefix(), response.getBirthDatePrefix());
        assertEquals(savedEntity.getBirthDateSuffix(), response.getBirthDateSuffix());
        assertEquals(savedEntity.getCounterFormat(), response.getCounterFormat());
    }
}
