package com.cintulova.TaskInsuranceApp.inputValidator;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class DateInTheFutureValidatorTest {

    private DateInTheFutureValidator dateInTheFutureValidator;

    @BeforeMethod
    public void setUp() {

        dateInTheFutureValidator = new DateInTheFutureValidator();

    }

    @Test
    public void returnTrueWhenDateIsInTheFuture() {

        // when & then
        assertTrue(dateInTheFutureValidator.isValidDateInTheFuture(LocalDate.now().plusDays(1)));

    }

    @Test
    public void returnFalseWhenDateIsNotInTheFuture() {

        // when & then
        assertFalse(dateInTheFutureValidator.isValidDateInTheFuture(LocalDate.now().minusDays(1)));

    }
}