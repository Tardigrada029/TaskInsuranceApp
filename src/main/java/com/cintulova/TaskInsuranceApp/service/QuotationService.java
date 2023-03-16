package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.DateInTheFutureValidator;
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

    public Quotation saveQuotation(Quotation quotation) {
        if (quotation.getDateOfSigningMortgage().isAfter(quotation.getBeginningOfInsurance())) {
            throw new IllegalArgumentException("Fill all the fields in correct format.");
        }
        return quotationRepository.save(quotation);
    }

}
