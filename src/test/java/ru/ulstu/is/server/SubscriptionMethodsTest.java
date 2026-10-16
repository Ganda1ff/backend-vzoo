package ru.ulstu.is.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.server.service.SubscriptionService;
import ru.ulstu.is.server.api.subscription.SubscriptionRq;
import ru.ulstu.is.server.error.NotFoundException;

@SpringBootTest
@Transactional
class SubscriptionMethodsTest {
    @Autowired SubscriptionService service;
    private SubscriptionRq sample() { return new SubscriptionRq("Анна", "anna@test.ru", "2026-10-01"); }
    @Test void create() { Assertions.assertNotNull(service.create(sample()).id()); }
    @Test void getEntity() { var a = service.create(sample()); Assertions.assertEquals(a.id(), service.getEntity(a.id()).getId()); }
    @Test void get() { var a = service.create(sample()); Assertions.assertEquals(a.id(), service.get(a.id()).id()); }
    @Test void getAll() { var a = service.create(sample()); Assertions.assertTrue(service.getAll().stream().anyMatch(v -> v.id().equals(a.id()))); }
    @Test void update() { var a = service.create(sample()); Assertions.assertEquals(a.id(), service.update(a.id(), new SubscriptionRq("Анна Б", "anna@test.ru", "2026-10-02")).id()); }
    @Test void delete() { var a = service.create(sample()); service.delete(a.id()); Assertions.assertThrows(NotFoundException.class, () -> service.get(a.id())); }
}
