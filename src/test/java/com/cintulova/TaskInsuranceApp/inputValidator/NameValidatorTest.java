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

        // when & then
        assertTrue(nameValidator.isValidName("Kate Green"));

    }

    @Test
    public void returnFalseWhenNameIsNotInCorrectFormat() {

        // when & then
        assertFalse(nameValidator.isValidName("Kate_Green"));

    }

    @Test
    public void returnFalseWhenThereIsNoName() {

        // when & then
        assertFalse(nameValidator.isValidName(""));

    }

    @Test
    public void returnTrueWhenMiddleNAmeIsInCorrectFormat() {

        // when & then
        assertTrue(nameValidator.isValidMiddleName("Patricia"));

    }

    @Test
    public void returnFalseWhenMiddleNAmeIsNotInCorrectFormat() {

        // when & then
        assertFalse(nameValidator.isValidMiddleName("Patricia8"));

    }

    @Test
    public void rteturnTrueWhenThereIsNoMiddleName() {

        // when & then
        assertTrue(nameValidator.isValidMiddleName(""));

    }
}