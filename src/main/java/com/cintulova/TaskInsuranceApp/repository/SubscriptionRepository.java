package com.cintulova.TaskInsuranceApp.repository;

import com.cintulova.TaskInsuranceApp.model.Subscription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
}
