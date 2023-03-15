package com.cintulova.TaskInsuranceApp.inputValidator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,})$");

    public Boolean isValidEmail (String email) {
        return EMAIL_REGEX.matcher(email).matches();
    }

}
