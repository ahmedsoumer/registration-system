package com.registration_system.configuration_service.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.registration_system.configuration_service.dto.ConfigurationRequest;
import com.registration_system.configuration_service.dto.ConfigurationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.registration_system.configuration_service.service.ConfigurationService;

@RestController
@RequestMapping("/api/config")
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping
    public ResponseEntity<ConfigurationResponse> saveConfiguration(@RequestBody @Valid ConfigurationRequest request) {
        ConfigurationResponse response = configurationService.saveConfiguration(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{configId}")
    public ResponseEntity<JsonNode> getConfigurationById(@PathVariable Long configId) {
        JsonNode configuration = configurationService.getConfigurationById(configId);
        return ResponseEntity.ok(configuration);
    }

    @GetMapping("/default")
    public ResponseEntity<JsonNode> getDefaultConfiguration() {
        JsonNode defaultConfig = configurationService.getDefaultConfiguration();
        return ResponseEntity.ok(defaultConfig);
    }

}
