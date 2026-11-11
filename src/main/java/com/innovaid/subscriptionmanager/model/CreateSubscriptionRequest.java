package com.innovaid.subscriptionmanager.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import java.time.LocalDate;

// Record indeholder implicit getter functioner, men ingen setters da de ikke kan ændres når de først er lavet.
public record CreateSubscriptionRequest (
    @NotNull Integer userId,
    @NotNull SubscriptionType type,
    @NotNull @FutureOrPresent LocalDate expiresAt,
    boolean autoRenew
){}
