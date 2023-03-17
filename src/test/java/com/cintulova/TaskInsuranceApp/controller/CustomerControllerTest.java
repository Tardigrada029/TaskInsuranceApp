package com.cintulova.TaskInsuranceApp.controller;

import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.service.CustomerService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

// TODO: I don't think they are working correctly
public class CustomerControllerTest {

    @Mock
    private CustomerService mockCustomerService;
    private CustomerController customerController;

    private final Long ID = 0L;
    private final String FIRST_NAME = "Kate";
    private final String LAST_NAME = "Green";
    private final String MIDDLE_NAME = "Patricia";
    private final String EMAIL = "kate.green@gmail.com";
    private final String PHONE_NUMBER = "+420122789098";
    private final LocalDate BIRTH_DATE = LocalDate.now().minusYears(30);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);
    private final NoSuchElementException NO_SUCH_ELEMENT_EXCEPTION = new NoSuchElementException("Error message.");
    private final IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION = new IllegalArgumentException("Error message.");

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        customerController = new CustomerController(mockCustomerService);

    }

    // ********** getAllCustomers() **********
    @Test
    public void testGetAllCustomers() {
        // given
        when(mockCustomerService.getAllCustomers()).thenReturn(Collections.singletonList(CUSTOMER_WITH_MIDDLE_NAME));

        // when
        Iterable<Customer> result = customerController.getAllCustomers();

        // then
        assertNotNull(result);

    }

    // ********** getCustomerById() **********
    @Test
    public void testGetCustomerById() {
        // given
        when(mockCustomerService.getCustomerById(ID)).thenReturn(Optional.of(CUSTOMER_WITH_MIDDLE_NAME));

        // when
        Optional<Customer> result = customerController.getCustomerById(ID);

        // then
        assertNotNull(result);

    }

    // ********** saveCustomer() **********
    @Test
    public void testSaveCustomer() {
        // given
        when(mockCustomerService.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);

        // when
        Customer result = customerController.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME);

        // then
        assertNotNull(result);

    }

    // ********** updateCustomerById() **********
    @Test
    public void testUpdateCustomerById() {
        // given
        when(mockCustomerService.updateCustomerById(CUSTOMER_WITH_MIDDLE_NAME, ID)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);

        // when
        Customer result = customerController.updateCustomerById(ID, CUSTOMER_WITH_MIDDLE_NAME);

        // then
        assertNotNull(result);

    }

    // ********** deleteCustomerById() **********
    @Test
    public void testDeleteCustomerById() {
        // given
        doNothing().when(mockCustomerService).deleteCustomerById(ID);

        // when
        customerController.deleteCustomerById(ID);

        // then
        verify(mockCustomerService, times(1)).deleteCustomerById(ID);

    }

    // ********** handleNoSuchElementException() **********
    @Test
    public void testHandleNoSuchElementException() {
        // given
        ResponseEntity<String> result = customerController.handleNoSuchElementException(NO_SUCH_ELEMENT_EXCEPTION);

        // when & then
        assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);

    }

    // ********** handleIllegalArgumentException() **********
    @Test
    public void testHandleIllegalArgumentException() {
        // given
        ResponseEntity<String> result = customerController.handleIllegalArgumentException(ILLEGAL_ARGUMENT_EXCEPTION);

        // when & then
        assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);

    }

}