package com.cintulova.TaskInsuranceApp.service;

import com.cintulova.TaskInsuranceApp.inputValidator.DateInTheFutureValidator;
import com.cintulova.TaskInsuranceApp.model.Subscription;
import com.cintulova.TaskInsuranceApp.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final DateInTheFutureValidator dateInTheFutureValidator;

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                               DateInTheFutureValidator dateInTheFutureValidator) {
        this.subscriptionRepository = subscriptionRepository;
        this.dateInTheFutureValidator = dateInTheFutureValidator;
    }

    public Subscription saveSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Optional<Subscription> getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id);
    }

}
