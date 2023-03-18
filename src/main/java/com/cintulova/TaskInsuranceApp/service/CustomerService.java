package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.*;
import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService  {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final EmailValidator emailValidator;
    private final NameValidator nameValidator;
    private final PhoneNumberValidator phoneNumberValidator;

    public CustomerService(CustomerRepository customerRepository,
                           EmailValidator emailValidator,
                           NameValidator nameValidator,
                           PhoneNumberValidator phoneNumberValidator) {
        this.customerRepository = customerRepository;
        this.emailValidator = emailValidator;
        this.nameValidator = nameValidator;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    public Customer saveCustomer(Customer customer) {
        logger.debug("Saving customer: " + customer);
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Customer with email " + customer.getEmail() + " already exists.");
        }
        if (!nameValidator.isValidName(customer.getFirstName()) ||
                !nameValidator.isValidMiddleName(customer.getMiddleName()) ||
                !nameValidator.isValidName(customer.getLastName()) ||
                !emailValidator.isValidEmail(customer.getEmail()) ||
                !phoneNumberValidator.isValidPhoneNumber(customer.getPhoneNumber()) ||
                customer.getBirthDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Fill all the fields in correct format.");
        }
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        logger.debug("Retrieving customer by id: " + id);
        if (customerRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        }
        return customerRepository.findById(id);
    }

    public Iterable<Customer> getAllCustomers() {
        logger.debug("Retrieving all customers");
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        logger.debug("Retrieving customer by email: " + email);
        if (customerRepository.findByEmail(email).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with email " + email + ".");
        }
        return customerRepository.findByEmail(email);
    }

}
