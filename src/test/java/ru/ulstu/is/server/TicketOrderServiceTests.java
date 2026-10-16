package ru.ulstu.is.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ru.ulstu.is.server.api.ticketorder.TicketOrderRq;
import ru.ulstu.is.server.api.tickettype.TicketTypeRq;
import ru.ulstu.is.server.repository.TicketOrderRepository;
import ru.ulstu.is.server.repository.TicketTypeRepository;
import ru.ulstu.is.server.service.TicketOrderService;
import ru.ulstu.is.server.service.TicketTypeService;

@SpringBootTest
@Transactional
class TicketOrderServiceTests {
    @Autowired TicketOrderService service;
    @Autowired TicketTypeService ticketTypeService;
    @Autowired TicketOrderRepository orderRepository;
    @Autowired TicketTypeRepository typeRepository;

    @BeforeEach void reset() {
        orderRepository.deleteAll();
        typeRepository.deleteAll();
    }

    @Test void crudAndRelationTest() {
        final var type = ticketTypeService.create(new TicketTypeRq("Взрослый", 600, "18+"));
        final var created = service.create(new TicketOrderRq("Иван", "ivan@example.com", type.id(), 2, "2026-10-10", 1200, "2026-10-01T12:00:00Z"));
        Assertions.assertEquals(type.id(), created.ticketType().id());
        final var updated = service.update(created.id(), new TicketOrderRq("Иван", "ivan@example.com", type.id(), 1, "2026-10-11", 600, "2026-10-01T12:00:00Z"));
        Assertions.assertEquals(1, updated.quantity());
        service.delete(created.id());
        Assertions.assertTrue(service.getAll().isEmpty());
    }
}
