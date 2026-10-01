package ru.ulstu.is.server.api.ticketorder;

import ru.ulstu.is.server.api.tickettype.TicketTypeRs;
import ru.ulstu.is.server.entity.TicketOrderEntity;

public record TicketOrderRs(
        String id,
        String visitorName,
        String email,
        String ticketTypeId,
        Integer quantity,
        String visitDate,
        Integer total,
        String createdAt,
        TicketTypeRs ticketType) {
    public static TicketOrderRs fromEntity(TicketOrderEntity entity) {
        final TicketTypeRs ticketType = TicketTypeRs.fromEntity(entity.getTicketType());
        return new TicketOrderRs(
                entity.getId(),
                entity.getVisitorName(),
                entity.getEmail(),
                entity.getTicketType().getId(),
                entity.getQuantity(),
                entity.getVisitDate(),
                entity.getTotal(),
                entity.getCreatedAt(),
                ticketType);
    }
}
