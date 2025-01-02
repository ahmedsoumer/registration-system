package com.registration_system.configuration_service.dto;

public class ConfigurationResponse {

    private Long id;
    private int lastNameLength;
    private String lastNamePrefix;
    private String lastNameSuffix;
    private int firstNameLength;
    private String firstNamePrefix;
    private String firstNameSuffix;
    private String birthDateFormat;
    private String birthDatePrefix;
    private String birthDateSuffix;
    private String counterFormat;

    public ConfigurationResponse(int lastNameLength, String lastNamePrefix, String lastNameSuffix, int firstNameLength, String firstNamePrefix, String firstNameSuffix, String birthDateFormat, String birthDatePrefix, String birthDateSuffix, String counterFormat, Long id) {
        this.lastNameLength = lastNameLength;
        this.lastNamePrefix = lastNamePrefix;
        this.lastNameSuffix = lastNameSuffix;
        this.firstNameLength = firstNameLength;
        this.firstNamePrefix = firstNamePrefix;
        this.firstNameSuffix = firstNameSuffix;
        this.birthDateFormat = birthDateFormat;
        this.birthDatePrefix = birthDatePrefix;
        this.birthDateSuffix = birthDateSuffix;
        this.counterFormat = counterFormat;
        this.id = id;
    }

    public ConfigurationResponse() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLastNameLength() {
        return lastNameLength;
    }

    public void setLastNameLength(int lastNameLength) {
        this.lastNameLength = lastNameLength;
    }

    public String getLastNamePrefix() {
        return lastNamePrefix;
    }

    public void setLastNamePrefix(String lastNamePrefix) {
        this.lastNamePrefix = lastNamePrefix;
    }

    public String getLastNameSuffix() {
        return lastNameSuffix;
    }

    public void setLastNameSuffix(String lastNameSuffix) {
        this.lastNameSuffix = lastNameSuffix;
    }

    public int getFirstNameLength() {
        return firstNameLength;
    }

    public void setFirstNameLength(int firstNameLength) {
        this.firstNameLength = firstNameLength;
    }

    public String getFirstNamePrefix() {
        return firstNamePrefix;
    }

    public void setFirstNamePrefix(String firstNamePrefix) {
        this.firstNamePrefix = firstNamePrefix;
    }

    public String getFirstNameSuffix() {
        return firstNameSuffix;
    }

    public void setFirstNameSuffix(String firstNameSuffix) {
        this.firstNameSuffix = firstNameSuffix;
    }

    public String getBirthDateFormat() {
        return birthDateFormat;
    }

    public void setBirthDateFormat(String birthDateFormat) {
        this.birthDateFormat = birthDateFormat;
    }

    public String getBirthDatePrefix() {
        return birthDatePrefix;
    }

    public void setBirthDatePrefix(String birthDatePrefix) {
        this.birthDatePrefix = birthDatePrefix;
    }

    public String getBirthDateSuffix() {
        return birthDateSuffix;
    }

    public void setBirthDateSuffix(String birthDateSuffix) {
        this.birthDateSuffix = birthDateSuffix;
    }

    public String getCounterFormat() {
        return counterFormat;
    }

    public void setCounterFormat(String counterFormat) {
        this.counterFormat = counterFormat;
    }
}
