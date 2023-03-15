package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.*;
import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService  {

    private final CustomerRepository customerRepository;
    private final DateInTheFutureValidator dateInTheFutureValidator;
    private final EmailValidator emailValidator;
    private final NameValidator nameValidator;
    private final PhoneNumberValidator phoneNumberValidator;

    public CustomerService(CustomerRepository customerRepository, DateInTheFutureValidator dateInTheFutureValidator, EmailValidator emailValidator, NameValidator nameValidator, PhoneNumberValidator phoneNumberValidator) {
        this.customerRepository = customerRepository;
        this.dateInTheFutureValidator = dateInTheFutureValidator;
        this.emailValidator = emailValidator;
        this.nameValidator = nameValidator;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    public Customer saveCustomer(Customer customer) {
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Customer with email " + customer.getEmail() + " already exists.");
        }
        if (!nameValidator.isValidName(customer.getFirstName()) || !nameValidator.isValidMiddleName(customer.getMiddleName()) || !nameValidator.isValidName(customer.getLastName()) || !emailValidator.isValidEmail(customer.getEmail()) || !phoneNumberValidator.isValidPhoneNumber(customer.getPhoneNumber()) || !dateInTheFutureValidator.isValidDateInTheFuture(customer.getBirthDate())) {
            throw new IllegalArgumentException("Fill all the fields in correct format.");
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> getUserById(Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        }
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        }
        customerRepository.deleteById(id);
    }

}
