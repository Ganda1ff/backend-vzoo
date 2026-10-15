package ru.ulstu.is.server.api.habitat;

import jakarta.validation.constraints.NotBlank;
import ru.ulstu.is.server.entity.HabitatEntity;
import jakarta.validation.constraints.Max;

public record HabitatRq(
        @NotBlank @Max(20) String name,
        @NotBlank String square,
        @NotBlank String climate) {
    public HabitatEntity toEntity() {
        final HabitatEntity entity = new HabitatEntity();
        applyTo(entity);
        return entity;
    }

    public void applyTo(HabitatEntity entity) {
        entity.setName(name);
        entity.setSquare(square);
        entity.setClimate(climate);
    }
}
