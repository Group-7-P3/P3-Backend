package com.innovaid.subscriptionmanager.repository;

import com.innovaid.subscriptionmanager.model.CreateSubscriptionRequest;
import com.innovaid.subscriptionmanager.model.Subscription;
import com.innovaid.subscriptionmanager.model.SubscriptionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository 
public class SubscriptionRepository {

    // Har ingen fucking ide hvad det her gør, men jdbc kræver at det er der
    private final JdbcTemplate jdbcTemplate;

    // Custom Row-til-object mapper
    // Laver et subscription object og putter alle elementerne fre row'et ind i objektet.
    private Subscription subscriptionRowMapper (ResultSet rs, int rowNum) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setLicenseKey(rs.getInt("license_key"));
        subscription.setUserId(rs.getInt("user_id"));
        subscription.setType(SubscriptionType.getTypeFromString(rs.getString("type")));
        subscription.setMaxDevices(rs.getInt("max_devices"));
        subscription.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        subscription.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        subscription.setExpiresAt(rs.getObject("expires_at", LocalDate.class));
        subscription.setAutoRenew(rs.getBoolean("auto_renew"));
        return subscription;
    }

    public SubscriptionRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveSubscription(CreateSubscriptionRequest subscriptionRequest){
        jdbcTemplate.update(
            "INSERT INTO subscription (user_id, type, expires_at, auto_renew) VALUES (?, ?::subscription_type, ?, ?)",
            subscriptionRequest.userId(),
            subscriptionRequest.type().getDbTypeValue(),
            subscriptionRequest.expiresAt(),
            subscriptionRequest.autoRenew()
        );
    }

    public void assignProduct(int licenseKey, int serialNumber){
        jdbcTemplate.update(
            "INSERT INTO subscription_to_product (license_key, serial_number) VALUES (?, ?)",
            licenseKey,
            serialNumber
        );
    }

    
    public Subscription getSubscription(int licenseKey){
        Subscription subscription = 
        jdbcTemplate.queryForObject(
            "SELECT * FROM subscription WHERE license_key = ?",
            this::subscriptionRowMapper,
            licenseKey
        );
        return subscription;
    }

    // Looper over alle subscriptions på en userId og samler dem i en liste.
    public List<Subscription> getSubscriptionsForUser(int userId){
        List<Subscription> subscriptions = 
        jdbcTemplate.query(
            "SELECT * FROM subscription WHERE user_id = ?", 
            this::subscriptionRowMapper,
            userId
        );
        return subscriptions;
    }

    




    

}
