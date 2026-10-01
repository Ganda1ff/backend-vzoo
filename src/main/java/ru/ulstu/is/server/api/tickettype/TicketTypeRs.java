package ru.ulstu.is.server.api.tickettype;

import ru.ulstu.is.server.entity.TicketTypeEntity;

public record TicketTypeRs(String id, String title, Integer price, String description) {
    public static TicketTypeRs fromEntity(TicketTypeEntity entity) {
        return new TicketTypeRs(entity.getId(), entity.getTitle(), entity.getPrice(), entity.getDescription());
    }
}
