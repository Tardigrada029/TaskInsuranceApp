package com.cintulova.TaskInsuranceApp.controller;

import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.service.QuotationService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

// TODO: I don't think they are working correctly
public class QuotationControllerTest {

    @Mock
    private QuotationService mockQuotationService;
    private QuotationController quotationController;

    private final Long ID = 0L;
    private final LocalDate DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS = LocalDate.now().plusMonths(2);
    private final Long INSURED_AMOUNT = 15000L;
    private final LocalDate DATE_IN_THE_FUTURE = LocalDate.now().plusDays(2);
    private final String FIRST_NAME = "Kate";
    private final String LAST_NAME = "Green";
    private final String MIDDLE_NAME = "Patricia";
    private final String EMAIL = "kate.green@gmail.com";
    private final String PHONE_NUMBER = "+420122789098";
    private final LocalDate BIRTH_DATE = LocalDate.now().minusYears(30);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);
    private final Quotation QUOTATION = new Quotation(ID, DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS, INSURED_AMOUNT, DATE_IN_THE_FUTURE, CUSTOMER_WITH_MIDDLE_NAME);
    private final NoSuchElementException NO_SUCH_ELEMENT_EXCEPTION = new NoSuchElementException("Error message.");
    private final IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION = new IllegalArgumentException("Error message.");

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        quotationController = new QuotationController(mockQuotationService);

    }

    // ********** saveQuotation() **********
    @Test
    public void testSaveQuotation() {
        // given
        when(mockQuotationService.saveQuotation(QUOTATION)).thenReturn(QUOTATION);

        // when
        Quotation result = quotationController.saveQuotation(QUOTATION);

        // then
        assertNotNull(result);

    }

    // ********** handleNoSuchElementException() **********
    @Test
    public void testHandleNoSuchElementException() {
        // given
        ResponseEntity<String> result = quotationController.handleNoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION);

        // when & then
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    // ********** handleIllegalArgumentException() **********
    @Test
    public void testHandleIllegalArgumentException() {
        // given
        ResponseEntity<String> result = quotationController.handleIllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);

        // when & then
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

}