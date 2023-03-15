package com.cintulova.TaskInsuranceApp.inputValidator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator {

    private static final Pattern INTERNATIONAL_PHONE_NUMBER_REGEX = Pattern.compile("^\\+(?:[0-9] ?){6,14}[0-9]$");

    public Boolean isValidPhoneNumber (String phoneNumber) {
        return INTERNATIONAL_PHONE_NUMBER_REGEX.matcher(phoneNumber).matches() && phoneNumber.length() >= 7 && phoneNumber.length() <= 14;
    }

}
