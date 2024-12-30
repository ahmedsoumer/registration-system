package com.registration_system.configuration_service.exception;

public class ConfigurationNotFoundException extends RuntimeException {
    public ConfigurationNotFoundException(String message) {
        super(message);
    }
}
