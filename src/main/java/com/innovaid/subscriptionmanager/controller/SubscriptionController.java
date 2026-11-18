package com.innovaid.subscriptionmanager.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
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

import com.innovaid.subscriptionmanager.model.CreateSubscriptionRequest;
import com.innovaid.subscriptionmanager.model.Subscription;
import com.innovaid.subscriptionmanager.repository.SubscriptionRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionController(SubscriptionRepository subscriptionRepository){
        this.subscriptionRepository = subscriptionRepository;
    }

    // @Valid checker om request body's værdier passer ind i CreateSubscriptionRequest recorden. Ellers rejecter den.
    @PostMapping
    public void createSubscription(@Valid @RequestBody CreateSubscriptionRequest subscriptionRequest ){
        subscriptionRepository.saveSubscription(subscriptionRequest);
    }

    @GetMapping("/{licenseKey}")
    public Subscription getSubscription(@PathVariable int licenseKey){
        try {
            Subscription subscription = subscriptionRepository.getSubscription(licenseKey);
            return subscription;
        }
        // Catch license_key ikke eksisterer
        catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "License key not found");
        } 
    }
    
    @GetMapping("/{licenseKey}/isExpired")
    public Boolean isSubscriptionExpired(@PathVariable int licenseKey){
        boolean expired;
        try {
            expired = subscriptionRepository.isSubscriptionExpired(licenseKey);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "License key not found");
        }
        return expired;
    }


    @GetMapping
    public List<Subscription> getSubscriptionsForUser(@RequestParam int userId){
        List<Subscription> subscriptions = subscriptionRepository.getSubscriptionsForUser(userId);
        return subscriptions;
    }

    // Product til subscription assigner
    @PostMapping("/{licenseKey}/assignProduct")
    public void assignProduct(@PathVariable int licenseKey, @RequestParam int serialNumber){
        // Check om subscription er udløbet
        boolean expired;
        try {
            expired = subscriptionRepository.isSubscriptionExpired(licenseKey);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the license key in database");
        }
        if (expired == true){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The subscription belonging to this license key is expired");
        }

        // Check om license key og serial number allerede er linked
        if (subscriptionRepository.isLicenseKeyAndSerialNumberLinked(licenseKey, serialNumber) == true){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This license key and serial number are already linked");
        }

        // Check om serial number allerede er linked
        if (subscriptionRepository.isSerialNumberLinked(serialNumber) == true){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This serial number is already linked to a license");
        }

        try {
            subscriptionRepository.assignProduct(licenseKey, serialNumber);
        }

        // Håndterer det meget usandsynlige edge case af to assignments af de samme produkt og licens key bliver sendt på præcis samme tid.
        catch (DuplicateKeyException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This license key and serial number are already linked");
        }

        catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Could not find the serial number in database");
        }
    }
}
