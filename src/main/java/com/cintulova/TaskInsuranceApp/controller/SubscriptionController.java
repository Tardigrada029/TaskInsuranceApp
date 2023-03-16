package com.cintulova.TaskInsuranceApp.controller;

import com.cintulova.TaskInsuranceApp.model.Subscription;
import com.cintulova.TaskInsuranceApp.service.SubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @PostMapping
    public Subscription saveSubscription(@RequestBody Subscription subscription) {
        return subscriptionService.saveSubscription(subscription);
    }

    @GetMapping("/{id}")
    public Optional<Subscription> getSubscriptionById(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }
}
