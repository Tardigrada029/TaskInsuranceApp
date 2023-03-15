package com.cintulova.TaskInsuranceApp.inputValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NameValidatorTest {

    NameValidator nameValidator;

    @BeforeMethod
    public void setUp() {

        nameValidator = new NameValidator();

    }

    @Test
    public void returnTrueWhenNameIsInCorrectFormat() {

        assertTrue(nameValidator.isValidName("Kate Green"));

    }

    @Test
    public void returnFalseWhenNameIsNotInCorrectFormat() {

        assertFalse(nameValidator.isValidName("Kate_Green"));

    }

    @Test
    public void returnFalseWhenThereIsNoName() {

        assertFalse(nameValidator.isValidName(""));

    }
}