package com.cintulova.TaskInsuranceApp.inputValidator;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateInTheFutureValidator {

    public Boolean isValidDateInTheFuture(LocalDate date) {
        LocalDate today = LocalDate.now();
        return date.isAfter(today);
    }

}
