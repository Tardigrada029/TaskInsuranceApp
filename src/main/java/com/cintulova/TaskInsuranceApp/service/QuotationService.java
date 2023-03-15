package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;

    public QuotationService(QuotationRepository quotationRepository) {
        this.quotationRepository = quotationRepository;
    }

    public void saveQuotation(Quotation quotation) {
        quotationRepository.save(quotation);
    }

    public Optional<Quotation> getQuotationById(Long id) {
        return quotationRepository.findById(id);
    }

    public void deleteQuotationById(Long id) {
        quotationRepository.deleteById(id);
    }
}
