package com.cintulova.TaskInsuranceApp.inputValidator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateInThePastValidator {

    public Boolean isValidDateInThePast(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }

}
