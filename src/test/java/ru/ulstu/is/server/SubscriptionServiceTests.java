package ru.ulstu.is.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.ulstu.is.server.api.subscription.SubscriptionRq;
import ru.ulstu.is.server.repository.SubscriptionRepository;
import ru.ulstu.is.server.service.SubscriptionService;

@SpringBootTest
class SubscriptionServiceTests {
    @Autowired SubscriptionService service;
    @Autowired SubscriptionRepository repository;

    @BeforeEach void reset() { repository.deleteAll(); }

    @Test void crudTest() {
        final var created = service.create(new SubscriptionRq("Иван", "ivan@example.com", "2026-10-01T12:00:00Z"));
        Assertions.assertEquals("ivan@example.com", created.email());
        final var updated = service.update(created.id(), new SubscriptionRq("Иван Петров", "ivan@example.com", "2026-10-01T12:00:00Z"));
        Assertions.assertEquals("Иван Петров", updated.name());
        service.delete(created.id());
        Assertions.assertTrue(service.getAll().isEmpty());
    }
}
