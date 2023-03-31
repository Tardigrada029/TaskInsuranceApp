package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.repository.QuotationRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class QuotationService {

    private static final Logger logger = LoggerFactory.getLogger(QuotationService.class);
    private final QuotationRepository quotationRepository;

    public Quotation saveQuotation(Quotation quotation) {
        logger.debug("Saving quotation: " + quotation);
        if (quotation.getDateOfSigningMortgage().isAfter(quotation.getBeginningOfInsurance())) {
            throw new IllegalArgumentException("Fill all the fields in correct format.");
        }
        return quotationRepository.save(quotation);
    }

}
