package ru.ulstu.is.server.api.subscription;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import ru.ulstu.is.server.entity.SubscriptionEntity;

public record SubscriptionRq(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String createdAt) {
    public SubscriptionEntity toEntity() {
        final SubscriptionEntity entity = new SubscriptionEntity();
        applyTo(entity);
        return entity;
    }

    public void applyTo(SubscriptionEntity entity) {
        entity.setName(name);
        entity.setEmail(email);
        entity.setCreatedAt(createdAt);
    }
}
