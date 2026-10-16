package ru.ulstu.is.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.server.entity.SubscriptionEntity;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, String> {
}
