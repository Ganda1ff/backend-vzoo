package ru.ulstu.is.server.api.tickettype;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.ulstu.is.server.entity.TicketTypeEntity;

public record TicketTypeRq(
        @NotBlank String title,
        @NotNull @Min(0) Integer price,
        @NotBlank String description) {
    public TicketTypeEntity toEntity() {
        final TicketTypeEntity entity = new TicketTypeEntity();
        applyTo(entity);
        return entity;
    }

    public void applyTo(TicketTypeEntity entity) {
        entity.setTitle(title);
        entity.setPrice(price);
        entity.setDescription(description);
    }
}
