package ru.ulstu.is.server.api.subscription;

import ru.ulstu.is.server.entity.SubscriptionEntity;

public record SubscriptionRs(String id, String name, String email, String createdAt) {
    public static SubscriptionRs fromEntity(SubscriptionEntity entity) {
        return new SubscriptionRs(entity.getId(), entity.getName(), entity.getEmail(), entity.getCreatedAt());
    }
}
