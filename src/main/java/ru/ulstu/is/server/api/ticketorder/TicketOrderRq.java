package ru.ulstu.is.server.api.ticketorder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.ulstu.is.server.entity.TicketOrderEntity;
import ru.ulstu.is.server.entity.TicketTypeEntity;
import jakarta.validation.constraints.Size;

public record TicketOrderRq(
        @NotBlank @Size(min = 2, max = 20) String visitorName,
        @NotBlank @Email String email,
        @NotBlank String ticketTypeId,
        @NotNull @Min(1) Integer quantity,
        @NotBlank String visitDate,
        @NotNull @Min(0) Integer total,
        @NotBlank String createdAt) {

    public TicketOrderEntity toEntity(TicketTypeEntity ticketType) {
        final TicketOrderEntity entity = new TicketOrderEntity();
        applyTo(entity, ticketType);
        return entity;
    }

    public void applyTo(TicketOrderEntity entity, TicketTypeEntity ticketType) {
        entity.setVisitorName(visitorName);
        entity.setEmail(email);
        entity.setTicketType(ticketType);
        entity.setQuantity(quantity);
        entity.setVisitDate(visitDate);
        entity.setTotal(total);
        entity.setCreatedAt(createdAt);
    }
}
