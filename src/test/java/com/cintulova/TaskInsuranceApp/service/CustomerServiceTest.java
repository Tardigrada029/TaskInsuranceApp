package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.DateInTheFutureValidator;
import com.cintulova.TaskInsuranceApp.inputValidator.EmailValidator;
import com.cintulova.TaskInsuranceApp.inputValidator.NameValidator;
import com.cintulova.TaskInsuranceApp.inputValidator.PhoneNumberValidator;
import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.repository.CustomerRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository mockCustomerRepository;
    @Mock
    private DateInTheFutureValidator mockDateInTheFutureValidator;
    @Mock
    private EmailValidator mockEmailValidator;
    @Mock
    private NameValidator mockNameValidator;
    @Mock
    private PhoneNumberValidator mockPhoneNumberValidator;
    private CustomerService customerService;

    private final Long ID = 0L;
    private final String FIRST_NAME = "Kate";
    private final String LAST_NAME = "Green";
    private final String MIDDLE_NAME = "Patricia";
    private final String EMAIL = "kate.green@gmail.com";
    private final String PHONE_NUMBER = "+420122789098";
    private final LocalDate BIRTH_DATE = LocalDate.now().minusYears(30);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);
    private final Customer CUSTOMER_WITHOUT_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);


    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(mockCustomerRepository, mockDateInTheFutureValidator, mockEmailValidator,
                mockNameValidator, mockPhoneNumberValidator);

    }

    @Test
    public void createNewCustomerWithMiddleName() {
        // given
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockDateInTheFutureValidator.isValidDateInTheFuture(BIRTH_DATE)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);

        // when & then
        assertEquals(customerService.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME), CUSTOMER_WITH_MIDDLE_NAME);

    }

}