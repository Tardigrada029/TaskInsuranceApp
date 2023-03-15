package com.cintulova.TaskInsuranceApp.inputValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class EmailValidatorTest {

    EmailValidator emailValidator;

    @BeforeMethod
    public void setUp() {

        emailValidator = new EmailValidator();

    }

    @Test
    public void returnTrueWhenEmailIsInCorrectFormat() {

        assertTrue(emailValidator.isValidEmail("kate.green@gmail.com"));

    }

    @Test
    public void returnFalseWhenEmailIsNotInCorrectFormat() {

        assertFalse(emailValidator.isValidEmail("kate.green@gmailcom"));

    }

    @Test
    public void returnFalseWhenThereIsNoEmail() {

        assertFalse(emailValidator.isValidEmail(""));

    }

}