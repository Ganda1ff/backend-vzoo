package ru.ulstu.is.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.ulstu.is.server.api.habitat.HabitatRq;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.HabitatRepository;
import ru.ulstu.is.server.service.HabitatService;

@SpringBootTest
class HabitatServiceTests {
    @Autowired HabitatService service;
    @Autowired HabitatRepository repository;

    @BeforeEach void reset() { repository.deleteAll(); }

    @Test void crudTest() {
        Assertions.assertThrows(NotFoundException.class, () -> service.get("1"));
        final var created = service.create(new HabitatRq("Тестовый вольер", "50", "Умеренный"));
        Assertions.assertEquals("1", created.id());
        Assertions.assertEquals(1, service.getAll().size());
        final var updated = service.update(created.id(), new HabitatRq("Новый вольер", "70", "Тёплый"));
        Assertions.assertEquals("Новый вольер", updated.name());
        service.delete(created.id());
        Assertions.assertTrue(service.getAll().isEmpty());
    }
}
