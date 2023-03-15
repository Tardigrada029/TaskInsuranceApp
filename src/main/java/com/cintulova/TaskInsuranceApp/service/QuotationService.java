package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.DateInTheFutureValidator;
import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final DateInTheFutureValidator dateInTheFutureValidator;

    public QuotationService(QuotationRepository quotationRepository,
                            DateInTheFutureValidator dateInTheFutureValidator) {
        this.quotationRepository = quotationRepository;
        this.dateInTheFutureValidator = dateInTheFutureValidator;
    }

    public Quotation saveQuotation(Quotation quotation) {
        return quotationRepository.save(quotation);
    }

    public Optional<Quotation> getQuotationById(Long id) {
        return quotationRepository.findById(id);
    }

    public void deleteQuotationById(Long id) {
        quotationRepository.deleteById(id);
    }
}
