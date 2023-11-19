package org.example.Utils;

import java.util.Objects;

public class Validator {
    public boolean validateName(String name) {
        return  isNonNull(name) && isNotEmpty(name) && hasValidCharacters(name)
                && hasNoWhitespaceOnly(name);
    }

    public boolean validateStreet(String street) {
        return  isNonNull(street) && isNotEmpty(street);
    }

    public boolean validateState(String state) {
        return  isNonNull(state) && isNotEmpty(state) && hasValidCharacters(state);
    }

    public boolean validateZip(String zip) {
        return isNonNull(zip) && isNotEmpty(zip) && containsOnlyDigits(zip);
    }

    public boolean validateCity(String city) {
        return  isNonNull(city) && isNotEmpty(city) && hasValidCharacters(city);

    }

    private boolean isNonNull(String dataString) {
        return !Objects.isNull(dataString);
    }

    private boolean isNotEmpty(String dataString) {
        return !dataString.isEmpty();
    }

    private boolean hasValidCharacters(String dataString) {
        return dataString.matches("[A-Za-z]+"); // Alphabetic characters only
    }

    private boolean hasNoWhitespaceOnly(String dataString) {
        return !dataString.trim().isEmpty();
    }

    private boolean containsOnlyDigits(String zipCode) {
        return zipCode.matches("\\d+");
    }

}
