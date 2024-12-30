package com.registration_system.numbering_service.dto;

public class NumberGenerationResponse {

    private String generatedNumber;

    public NumberGenerationResponse(String generatedNumber) {
        this.generatedNumber = generatedNumber;
    }

    public NumberGenerationResponse() {
    }

    // Getter
    public String getGeneratedNumber() {
        return generatedNumber;
    }
}
