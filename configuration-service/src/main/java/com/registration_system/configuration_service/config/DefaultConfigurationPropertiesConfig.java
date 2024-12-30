package com.registration_system.configuration_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Configuration
@EnableConfigurationProperties(DefaultConfigurationProperties.class)
public class DefaultConfigurationPropertiesConfig {
    @Bean
    public DefaultConfigurationProperties defaultConfigurationProperties() {
        return new DefaultConfigurationProperties();
    }
}
