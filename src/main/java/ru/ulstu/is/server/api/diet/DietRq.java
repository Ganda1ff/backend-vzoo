package ru.ulstu.is.server.api.diet;

import jakarta.validation.constraints.NotBlank;
import ru.ulstu.is.server.entity.DietEntity;

public record DietRq(@NotBlank String name) {
    public DietEntity toEntity() {
        final DietEntity entity = new DietEntity();
        applyTo(entity);
        return entity;
    }

    public void applyTo(DietEntity entity) {
        entity.setName(name);
    }
}
