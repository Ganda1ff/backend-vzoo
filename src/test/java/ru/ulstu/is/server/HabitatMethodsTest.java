package ru.ulstu.is.server;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.server.service.HabitatService;
import ru.ulstu.is.server.api.habitat.HabitatRq;
import ru.ulstu.is.server.error.NotFoundException;

@SpringBootTest
@Transactional
class HabitatMethodsTest {
    @Autowired HabitatService service;
    private HabitatRq sample() { return new HabitatRq("Тропики", "150", "Тёплый"); }
    @Test void create() { Assertions.assertNotNull(service.create(sample()).id()); }
    @Test void getEntity() { var a = service.create(sample()); Assertions.assertEquals(a.id(), service.getEntity(a.id()).getId()); }
    @Test void get() { var a = service.create(sample()); Assertions.assertEquals(a.id(), service.get(a.id()).id()); }
    @Test void getAll() { var a = service.create(sample()); Assertions.assertTrue(service.getAll().stream().anyMatch(v -> v.id().equals(a.id()))); }
    @Test void update() { var a = service.create(sample()); Assertions.assertEquals(a.id(), service.update(a.id(), new HabitatRq("Лес", "200", "Умеренный")).id()); }
    @Test void delete() { var a = service.create(sample()); service.delete(a.id()); Assertions.assertThrows(NotFoundException.class, () -> service.get(a.id())); }
}
