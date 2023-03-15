package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.*;
import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

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

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Optional<Customer> getUserById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

}
