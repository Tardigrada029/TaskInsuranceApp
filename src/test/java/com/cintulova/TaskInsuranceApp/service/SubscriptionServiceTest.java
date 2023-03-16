package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.model.Subscription;
import com.cintulova.TaskInsuranceApp.repository.SubscriptionRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class SubscriptionServiceTest {

    @Mock
    private SubscriptionRepository mockSubscriptionRepository;
    private SubscriptionService subscriptionService;

    private final Long ID = 0L;
    private final Long INSURED_AMOUNT = 15000L;
    private final String FIRST_NAME = "Kate";
    private final String LAST_NAME = "Green";
    private final String MIDDLE_NAME = "Patricia";
    private final String EMAIL = "kate.green@gmail.com";
    private final String PHONE_NUMBER = "+420122789098";
    private final LocalDate BIRTH_DATE = LocalDate.now().minusYears(30);
    private final LocalDate DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS = LocalDate.now().plusMonths(2);
    private final LocalDate DATE_IN_THE_FUTURE = LocalDate.now().plusDays(2);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);
    private final Quotation QUOTATION = new Quotation(ID, DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS, INSURED_AMOUNT, DATE_IN_THE_FUTURE, CUSTOMER_WITH_MIDDLE_NAME);
    private final Subscription SUBSCRIPTION = new Subscription(ID, DATE_IN_THE_FUTURE, DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS, QUOTATION);
    private final Subscription SUBSCRIPTION_WRONG_DATES = new Subscription(ID, DATE_IN_THE_FUTURE_PLUS_TWO_MONTHS, DATE_IN_THE_FUTURE, QUOTATION);


    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        subscriptionService = new SubscriptionService(mockSubscriptionRepository);

    }

    // ********** saveSubscription() **********
    @Test
    public void createNewSubscription() {
        // given
        when(mockSubscriptionRepository.save(SUBSCRIPTION)).thenReturn(SUBSCRIPTION);

        // when & then
        assertEquals(subscriptionService.saveSubscription(SUBSCRIPTION), SUBSCRIPTION);
    }

    @Test
    public void throwIllegalArgumentExceptionWithWrongDatesWhileCreatingNewSubscription() {
        // given
        when(mockSubscriptionRepository.save(SUBSCRIPTION_WRONG_DATES)).thenReturn(SUBSCRIPTION_WRONG_DATES);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> subscriptionService.saveSubscription(SUBSCRIPTION_WRONG_DATES));

    }

    // ********** getSubscriptionById() **********
    @Test
    public void returnExistingSavedSubscriptionWithGivenId() {
        // given
        when(mockSubscriptionRepository.findById(ID)).thenReturn(Optional.of(SUBSCRIPTION));

        // when & then
        assertEquals(subscriptionService.getSubscriptionById(ID), Optional.of(SUBSCRIPTION));

    }

    @Test
    public void throwNoSuchElementExceptionWhenThereIsNoSavedSubscriptionWithGivenId() {
        // given
        when(mockSubscriptionRepository.findById(ID)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class, () -> subscriptionService.getSubscriptionById(ID));

    }

}