package com.cintulova.TaskInsuranceApp.repository;

import com.cintulova.TaskInsuranceApp.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
