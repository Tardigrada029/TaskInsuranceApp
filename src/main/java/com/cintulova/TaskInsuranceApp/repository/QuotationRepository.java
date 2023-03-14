package com.cintulova.TaskInsuranceApp.repository;

import com.cintulova.TaskInsuranceApp.model.Quotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepository extends CrudRepository<Quotation, Long> {
}
