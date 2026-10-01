package ru.ulstu.is.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.ulstu.is.server.api.tickettype.TicketTypeRq;
import ru.ulstu.is.server.repository.TicketTypeRepository;
import ru.ulstu.is.server.service.TicketTypeService;

@SpringBootTest
class TicketTypeServiceTests {
    @Autowired TicketTypeService service;
    @Autowired TicketTypeRepository repository;

    @BeforeEach void reset() { repository.deleteAll(); }

    @Test void crudTest() {
        final var created = service.create(new TicketTypeRq("Тестовый", 100, "Описание"));
        Assertions.assertEquals(100, created.price());
        final var updated = service.update(created.id(), new TicketTypeRq("Тестовый", 150, "Новое описание"));
        Assertions.assertEquals(150, updated.price());
        service.delete(created.id());
        Assertions.assertTrue(service.getAll().isEmpty());
    }
}
