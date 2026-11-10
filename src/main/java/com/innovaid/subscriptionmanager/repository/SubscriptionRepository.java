package com.innovaid.subscriptionmanager.repository;

import com.innovaid.subscriptionmanager.model.Subscription;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository 
public class SubscriptionRepository {

    // Har ingen fucking ide hvad det her gør, men jdbc kræver at det er der
    private final JdbcTemplate jdbcTemplate;

    public SubscriptionRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveSubscription(Subscription subscription){
        jdbcTemplate.update(
            "INSERT INTO subscription (user_id, type, expires_at, auto_renew) VALUES (?, ?, ?, ?)",
            subscription.getUserId(),
            subscription.getType(),
            subscription.getExpiresAt(),
            subscription.isAutoRenew()
        );
    }

    
    public Subscription getSubscription(int licenseKey){
        Subscription subscription = 
        jdbcTemplate.queryForObject(
            "SELECT * FROM subscription WHERE license_key = ?",
            new BeanPropertyRowMapper<>(Subscription.class), // Laver et subscription object og putter alle elementerne fre row'et ind i objektet.
            licenseKey
        );
        return subscription;
    }

    // Looper over alle subscriptions på en userId og samler dem i en liste.
    public List<Subscription> getSubscriptionsForUser(int userId){
        List<Subscription> subscriptions = 
        jdbcTemplate.query(
            "SELECT * FROM subscription WHERE user_id = ?", 
            new BeanPropertyRowMapper<>(Subscription.class),
            userId
        );
        return subscriptions;
    }




    

}
