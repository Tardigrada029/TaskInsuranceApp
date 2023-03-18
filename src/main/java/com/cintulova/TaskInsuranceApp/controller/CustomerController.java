package com.cintulova.TaskInsuranceApp.controller;

import com.cintulova.TaskInsuranceApp.model.Customer;
import com.cintulova.TaskInsuranceApp.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomerById(@RequestBody Customer customer, @PathVariable Long id) {
        Optional<Customer> previousCustomer = customerService.getCustomerById(id);
        if (previousCustomer.isEmpty()) {
            throw new NoSuchElementException("Could not find customer with id " + id + ".");
        } else {
            Customer existingCustomer = previousCustomer.get();
            if (customer.getFirstName() != null) {
                existingCustomer.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null) {
                existingCustomer.setLastName(customer.getLastName());
            }
            if (customer.getMiddleName() != null) {
                existingCustomer.setMiddleName(customer.getMiddleName());
            }
            if (customer.getEmail() != null) {
                existingCustomer.setEmail(customer.getEmail());
            }
            if (customer.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            }
            if (customer.getBirthDate() != null) {
                existingCustomer.setBirthDate(customer.getBirthDate());
            }
            return customerService.updateCustomerById(existingCustomer);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

}
