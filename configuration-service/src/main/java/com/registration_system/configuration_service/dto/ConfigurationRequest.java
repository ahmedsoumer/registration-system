package com.registration_system.configuration_service.dto;

public class ConfigurationRequest {

    private int firstNameLength;
    private String firstNamePrefix;
    private String firstNameSuffix;

    private int lastNameLength;
    private String lastNamePrefix;
    private String lastNameSuffix;

    private String birthDateFormat;
    private String birthDatePrefix;
    private String birthDateSuffix;

    private String counterFormat;


    public ConfigurationRequest(int lastNameLength, String lastNamePrefix, String lastNameSuffix, int firstNameLength, String firstNamePrefix, String firstNameSuffix, String birthDateFormat, String birthDatePrefix, String birthDateSuffix, String counterFormat) {
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
    }

    public ConfigurationRequest() {
    }

    public int getLastNameLength() {
        return lastNameLength;
    }

    public void setLastNameLength(int nameLength) {
        this.lastNameLength = nameLength;
    }

    public String getLastNamePrefix() {
        return lastNamePrefix;
    }

    public void setLastNamePrefix(String namePrefix) {
        this.lastNamePrefix = namePrefix;
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
