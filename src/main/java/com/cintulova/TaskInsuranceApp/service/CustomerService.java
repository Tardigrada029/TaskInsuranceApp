package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.*;
import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService  {

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
        if (customerRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        }
        return customerRepository.findById(id);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        if (customerRepository.findByEmail(email).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with email " + email + ".");
        }
        return customerRepository.findByEmail(email);
    }

    public Customer updateCustomerById(Customer customer, Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        }
        customerRepository.deleteById(id);
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        if (customerRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        }
        customerRepository.deleteById(id);
    }

}
