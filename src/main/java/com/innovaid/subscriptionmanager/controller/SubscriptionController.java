package com.innovaid.subscriptionmanager.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import com.innovaid.subscriptionmanager.model.Subscription;
import com.innovaid.subscriptionmanager.repository.SubscriptionRepository;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    @PostMapping
    public Subscription createSubscription(@RequestBody Subscription subscription){
        subscriptionRepository.saveSubscription(subscription);
        return subscription;
    }

    @GetMapping("/{licenseKey}")
    public Subscription getSubscription(@PathVariable int licenseKey){
        try {
            Subscription subscription = subscriptionRepository.getSubscription(licenseKey);
            return subscription;
        }
        // Catch license_key ikke eksisterer
        catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subscription not found");
        } 
    }

    @GetMapping
    public List<Subscription> getSubscriptionsForUser(@RequestParam int userId){
        List<Subscription> subscriptions = subscriptionRepository.getSubscriptionsForUser(userId);
        return subscriptions;
    }
    
}
