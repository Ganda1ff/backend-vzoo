package ru.ulstu.is.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.server.api.ticketorder.TicketOrderRq;
import ru.ulstu.is.server.api.tickettype.TicketTypeRq;
import ru.ulstu.is.server.service.*;
import ru.ulstu.is.server.error.NotFoundException;

@SpringBootTest
@Transactional
class TicketOrderMethodsTest {
    @Autowired TicketOrderService service;
    @Autowired TicketTypeService types;
    private TicketOrderRq sample() {
        var type = types.create(new TicketTypeRq("Взрослый", 600, "Полный"));
        return new TicketOrderRq("Олег", "oleg@test.ru", type.id(), 2, "2026-10-20", 1200, "2026-10-10");
    }
    @Test void create() { assertNotNull(service.create(sample()).id()); }
    @Test void getEntity() { var a = service.create(sample()); assertEquals(a.id(), service.getEntity(a.id()).getId()); }
    @Test void get() { var a = service.create(sample()); assertEquals(a.id(), service.get(a.id()).id()); }
    @Test void getAll() { var a = service.create(sample()); assertTrue(service.getAll().stream().anyMatch(v -> v.id().equals(a.id()))); }
    @Test void update() { var q = sample(); var a = service.create(q); assertEquals(1, service.update(a.id(), new TicketOrderRq(q.visitorName(), q.email(), q.ticketTypeId(), 1, q.visitDate(), 600, q.createdAt())).quantity()); }
    @Test void delete() { var a = service.create(sample()); service.delete(a.id()); assertThrows(NotFoundException.class, () -> service.get(a.id())); }
    @Test void getSalesStatistics() { service.create(sample()); assertTrue(service.getSalesStatistics("2026-01-01").stream().anyMatch(s -> s.getRevenue() >= 1200)); }
    @Test void bidirectionalAssociation() { var a = service.create(sample()); var e = service.getEntity(a.id()); assertTrue(e.getTicketType().getOrders().contains(e)); }
}
