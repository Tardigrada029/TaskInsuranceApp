package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService  {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
