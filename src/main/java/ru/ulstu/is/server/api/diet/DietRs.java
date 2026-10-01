package ru.ulstu.is.server.api.diet;

import ru.ulstu.is.server.entity.DietEntity;

public record DietRs(String id, String name) {
    public static DietRs fromEntity(DietEntity entity) {
        return new DietRs(entity.getId(), entity.getName());
    }
}
