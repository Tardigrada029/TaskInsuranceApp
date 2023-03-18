package com.cintulova.TaskInsuranceApp.service;

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
import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository mockCustomerRepository;
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
    private final String EMPTY_MIDDLE_NAME = "";
    private final String EMAIL = "kate.green@gmail.com";
    private final String PHONE_NUMBER = "+420122789098";
    private final LocalDate BIRTH_DATE = LocalDate.now().minusYears(30);
    private final LocalDate DATE_IN_THE_FUTURE = LocalDate.now().plusMonths(5);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);
    private final Customer CUSTOMER_WITH_MIDDLE_NAME_WRONG_BIRTH_DATE = new Customer(ID, FIRST_NAME, LAST_NAME, MIDDLE_NAME, EMAIL, PHONE_NUMBER, DATE_IN_THE_FUTURE);
    private final Customer CUSTOMER_WITHOUT_MIDDLE_NAME = new Customer(ID, FIRST_NAME, LAST_NAME, EMPTY_MIDDLE_NAME, EMAIL, PHONE_NUMBER, BIRTH_DATE);


    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(mockCustomerRepository, mockEmailValidator,
                mockNameValidator, mockPhoneNumberValidator);

    }

    // ********** saveCustomer() **********
    @Test
    public void createNewCustomerWithMiddleName() {
        // given
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);

        // when & then
        assertEquals(customerService.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME), CUSTOMER_WITH_MIDDLE_NAME);

    }

    @Test
    public void createNewCustomerWithoutMiddleName() {
        // given
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(EMPTY_MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITHOUT_MIDDLE_NAME)).thenReturn(CUSTOMER_WITHOUT_MIDDLE_NAME);

        // when & then
        assertEquals(customerService.saveCustomer(CUSTOMER_WITHOUT_MIDDLE_NAME), CUSTOMER_WITHOUT_MIDDLE_NAME);

    }

    @Test
    public void throwIllegalArgumentExceptionWhileCreatingNewCustomerWithExistingEmail() {
        // given
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);
        when(mockCustomerRepository.findByEmail(EMAIL)).thenReturn(Optional.of(CUSTOMER_WITH_MIDDLE_NAME));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> customerService.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME));

    }

    @Test
    public void throwIllegalArgumentExceptionWhileCreatingNewCustomerWithBirthDateInTheFuture() {
        // given
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME_WRONG_BIRTH_DATE)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME_WRONG_BIRTH_DATE);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> customerService.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME_WRONG_BIRTH_DATE));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenThereIsBlankVariableWhileCreatingNewCustomer() {
        // given
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(false);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);

        // when & then
        assertThrows(IllegalArgumentException.class, () -> customerService.saveCustomer(CUSTOMER_WITH_MIDDLE_NAME));

    }

    // ********** getAllCustomers() **********
    @Test
    public void returnAllExistingCustomers() {
        // given
        when(mockCustomerRepository.findAll()).thenReturn(Collections.singletonList(CUSTOMER_WITH_MIDDLE_NAME));

        // when & then
        assertEquals(customerService.getAllCustomers(), Collections.singletonList(CUSTOMER_WITH_MIDDLE_NAME));
    }

    // ********** getCustomerById() **********
    @Test
    public void returnExistingSavedCustomerWithGivenId() {
        // given
        when(mockCustomerRepository.findById(ID)).thenReturn(Optional.of(CUSTOMER_WITH_MIDDLE_NAME));

        // when & then
        assertEquals(customerService.getCustomerById(ID), Optional.of(CUSTOMER_WITH_MIDDLE_NAME));

    }

    @Test
    public void throwNoSuchElementExceptionWhenThereIsNoSavedCustomerWithGivenId() {
        // given
        when(mockCustomerRepository.findById(ID)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class, () -> customerService.getCustomerById(ID));

    }

    // ********** getCustomerByEmail() **********
    @Test
    public void returnExistingSavedCustomerWithGivenEmail() {
        // given
        when(mockCustomerRepository.findByEmail(EMAIL)).thenReturn(Optional.of(CUSTOMER_WITH_MIDDLE_NAME));

        // when & then
        assertEquals(customerService.getCustomerByEmail(EMAIL), Optional.of(CUSTOMER_WITH_MIDDLE_NAME));

    }

    @Test
    public void throwNoSuchElementExceptionWhenThereIsNoSavedCustomerWithGivenEmail() {
        // given
        when(mockCustomerRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());

        // when & then
        assertThrows(NoSuchElementException.class, () -> customerService.getCustomerByEmail(EMAIL));

    }

    // TODO: update test
    // ********** updateCustomerById() **********
    @Test
    public void updateExistingUserWithGivenId() {
        // given
        when(mockCustomerRepository.findById(ID)).thenReturn(Optional.of(CUSTOMER_WITH_MIDDLE_NAME));
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);
        doNothing().when(mockCustomerRepository).deleteById(ID);

        // when & then
        assertEquals(customerService.updateCustomerById(CUSTOMER_WITH_MIDDLE_NAME), CUSTOMER_WITH_MIDDLE_NAME);

    }

    @Test
    public void throwNoSuchElementExceptionWhenUserWithGivenIdIsNotPresentToUpdate() {
        // given
        when(mockCustomerRepository.findById(ID)).thenReturn(Optional.empty());
        when(mockNameValidator.isValidName(FIRST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidName(LAST_NAME)).thenReturn(true);
        when(mockNameValidator.isValidMiddleName(MIDDLE_NAME)).thenReturn(true);
        when(mockEmailValidator.isValidEmail(EMAIL)).thenReturn(true);
        when(mockPhoneNumberValidator.isValidPhoneNumber(PHONE_NUMBER)).thenReturn(true);
        when(mockCustomerRepository.save(CUSTOMER_WITH_MIDDLE_NAME)).thenReturn(CUSTOMER_WITH_MIDDLE_NAME);
        doNothing().when(mockCustomerRepository).deleteById(ID);

        // when & then
        assertThrows(NoSuchElementException.class, () -> customerService.updateCustomerById(CUSTOMER_WITH_MIDDLE_NAME));
    }

    // ********** deleteCustomerById() **********
    @Test
    public void deleteExistingUserWithGivenId() {
        // given
        when(mockCustomerRepository.findById(ID)).thenReturn(Optional.of(CUSTOMER_WITH_MIDDLE_NAME));
        when(mockCustomerRepository.findByEmail(CUSTOMER_WITH_MIDDLE_NAME.getEmail())).thenReturn(Optional.empty());
        doNothing().when(mockCustomerRepository).deleteById(ID);

        // when & then TODO (I don't think it works correctly)
        customerService.deleteCustomerById(ID);
        assertThrows(NoSuchElementException.class, () -> customerService.getCustomerByEmail(EMAIL));
    }


    @Test
    public void throwNoSuchElementExceptionWhenUserWithGivenIdIsNotPresentToDelete() {
        // given
        when(mockCustomerRepository.findById(ID)).thenReturn(Optional.empty());
        doNothing().when(mockCustomerRepository).deleteById(ID);

        // when & then
        assertThrows(NoSuchElementException.class, () -> customerService.deleteCustomerById(ID));

    }

}