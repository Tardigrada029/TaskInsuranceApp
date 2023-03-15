package com.cintulova.TaskInsuranceApp.inputValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class DateInTheFutureValidatorTest {

    DateInTheFutureValidator dateInTheFutureValidator;

    @BeforeMethod
    public void setUp() {

        dateInTheFutureValidator = new DateInTheFutureValidator();

    }

    @Test
    public void returnTrueWhenDateIsInTheFuture() {

        assertTrue(dateInTheFutureValidator.isValidDateInTheFuture(LocalDate.now().plusDays(30)));

    }

    @Test
    public void returnFalseWhenDateIsNotInTheFuture() {

        assertFalse(dateInTheFutureValidator.isValidDateInTheFuture(LocalDate.now().minusDays(30)));

    }
}