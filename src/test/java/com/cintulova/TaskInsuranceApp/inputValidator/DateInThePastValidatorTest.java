package com.cintulova.TaskInsuranceApp.inputValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class DateInThePastValidatorTest {

    DateInThePastValidator dateInThePastValidator;

    @BeforeMethod
    public void setUp() {

        dateInThePastValidator = new DateInThePastValidator();

    }

    @Test
    public void returnTrueWhenDateIsInThePast() {

        assertTrue(dateInThePastValidator.isValidDateInThePast(LocalDate.now().minusDays(1)));

    }

    @Test
    public void returnFalseWhenDateIsNotInThePast() {

        assertFalse(dateInThePastValidator.isValidDateInThePast(LocalDate.now().plusDays(1)));

    }
}