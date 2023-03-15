package com.cintulova.TaskInsuranceApp.inputValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PhoneNumberValidatorTest {

    PhoneNumberValidator phoneNumberValidator;

    @BeforeMethod
    public void setUp() {

        phoneNumberValidator = new PhoneNumberValidator();

    }

    @Test
    public void returnTrueWhenPhoneNumberIsInCorrectFormat() {

        assertTrue(phoneNumberValidator.isValidPhoneNumber("+420122789098"));

    }

    @Test
    public void returnFalseWhenPhoneNumberIsNotInCorrectFormat() {

        assertFalse(phoneNumberValidator.isValidPhoneNumber("00420122789098"));

    }

    @Test
    public void returnFalseWhenThereIsNoPhoneNumber() {

        assertFalse(phoneNumberValidator.isValidPhoneNumber(""));

    }
}