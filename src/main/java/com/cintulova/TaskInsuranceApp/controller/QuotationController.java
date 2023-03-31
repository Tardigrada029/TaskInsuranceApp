package com.cintulova.TaskInsuranceApp.controller;

import com.cintulova.TaskInsuranceApp.model.Quotation;
import com.cintulova.TaskInsuranceApp.service.CustomerService;
import com.cintulova.TaskInsuranceApp.service.QuotationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    private static final Logger logger = LoggerFactory.getLogger(QuotationController.class);
    private final QuotationService quotationService;

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @PostMapping
    public Quotation saveQuotation(@RequestBody Quotation quotation) {
        logger.debug("Saving quotation: " + quotation);
        return quotationService.saveQuotation(quotation);
    }

}
