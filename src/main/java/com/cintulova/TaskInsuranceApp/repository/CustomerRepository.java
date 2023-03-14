package com.cintulova.TaskInsuranceApp.repository;

import com.cintulova.TaskInsuranceApp.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
