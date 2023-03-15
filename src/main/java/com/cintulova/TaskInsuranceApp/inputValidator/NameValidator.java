package com.cintulova.TaskInsuranceApp.inputValidator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class NameValidator {

    private static final Pattern INTERNATIONAL_NAME_REGEX = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

    public Boolean isValidName (String name) {
        return INTERNATIONAL_NAME_REGEX.matcher(name).matches();
    }

}
