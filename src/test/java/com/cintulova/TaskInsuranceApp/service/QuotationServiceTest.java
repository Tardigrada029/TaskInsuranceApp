package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.repository.QuotationRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class QuotationServiceTest {

    @Mock
    private QuotationRepository mockQuotationRepository;
    private QuotationService quotationService;

    private final Long ID = 0L;
    private final String FIRST_NAME = "Kate";
    private final String LAST_NAME = "Green";
    private final String MIDDLE_NAME = "Patricia";
    private final String EMAIL = "kate.green@gmail.com";
    private final String PHONE_NUMBER = "+420122789098";
    private final LocalDate BIRTH_DATE = LocalDate.now().minusYears(30);
    private final LocalDate DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS = LocalDate.now().plusMonths(2);
    private final Long INSURED_AMOUNT = 15000L;
    private final LocalDate DATE_IN_THE_FUTURE = LocalDate.now().plusDays(2);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);
    private final Quotation QUOTATION = new Quotation(ID, DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS, INSURED_AMOUNT, DATE_IN_THE_FUTURE, CUSTOMER_WITH_MIDDLE_NAME);
    private final Quotation QUOTATION_WRONG_DATES = new Quotation(ID, DATE_IN_THE_FUTURE, INSURED_AMOUNT, DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS, CUSTOMER_WITH_MIDDLE_NAME);


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        quotationService = new QuotationService(mockQuotationRepository);
    }

    // ********** saveQuotation() **********
    @Test
    public void createNewQuotation() {
        // given
        when(mockQuotationRepository.save(QUOTATION)).thenReturn(QUOTATION);

        // when & then
        assertEquals(quotationService.saveQuotation(QUOTATION), QUOTATION);

    }

    @Test
    public void throwIllegalArgumentExceptionWhenSigningMortgageIsAfterBeginningInsuranceWhileCreatingNewQuotation() {
        // given
        when(mockQuotationRepository.save(QUOTATION_WRONG_DATES)).thenReturn(QUOTATION_WRONG_DATES);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> quotationService.saveQuotation(QUOTATION_WRONG_DATES));

    }
}