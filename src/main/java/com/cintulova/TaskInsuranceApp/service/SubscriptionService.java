package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.model.Subscription;
import com.cintulova.TaskInsuranceApp.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription saveSubscription(Subscription subscription) {
        if (subscription.getStartDate().isAfter(subscription.getValidUntil())) {
            throw new IllegalArgumentException("Fill all the fields in correct format.");
        }
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscriptionById(@PathVariable Long id) {
        if (subscriptionRepository.findById(id).isEmpty()) {
            throw new NoSuchElementException("Could not find subscription with id " + id + ".");
        }
        return subscriptionRepository.findById(id);
    }

}
