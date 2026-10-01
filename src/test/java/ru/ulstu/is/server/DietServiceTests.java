package ru.ulstu.is.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.ulstu.is.server.api.diet.DietRq;
import ru.ulstu.is.server.repository.DietRepository;
import ru.ulstu.is.server.service.DietService;

@SpringBootTest
class DietServiceTests {
    @Autowired DietService service;
    @Autowired DietRepository repository;

    @BeforeEach void reset() { repository.deleteAll(); }

    @Test void crudTest() {
        final var created = service.create(new DietRq("Тестовое питание"));
        Assertions.assertEquals("Тестовое питание", service.get(created.id()).name());
        final var updated = service.update(created.id(), new DietRq("Новое питание"));
        Assertions.assertEquals("Новое питание", updated.name());
        service.delete(created.id());
        Assertions.assertTrue(service.getAll().isEmpty());
    }
}
