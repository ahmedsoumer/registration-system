package com.registration_system.numbering_service.config;


import com.registration_system.numbering_service.client.ConfigurationClient;
import com.registration_system.numbering_service.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfig {

    @Bean
    public NumberGenerationStrategy firstNameGenerationStrategy(ConfigurationClient clientConfig) {
        return new FirstNameGenerationStrategy(clientConfig);
    }

    @Bean
    public NumberGenerationStrategy lastNameGenerationStrategy(ConfigurationClient clientConfig) {
        return new LastNameGenerationStrategy(clientConfig);
    }

    @Bean
    public NumberGenerationStrategy birthDateGenerationStrategy(ConfigurationClient clientConfig) {
        return new BirthdateGenerationStrategy(clientConfig);
    }

    @Bean
    public NumberGenerationStrategy counterGenerationStrategy(ConfigurationClient clientConfig) {
        return new CounterGenerationStrategy(clientConfig);
    }
}
