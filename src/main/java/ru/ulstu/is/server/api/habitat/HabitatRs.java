package ru.ulstu.is.server.api.habitat;

import ru.ulstu.is.server.entity.HabitatEntity;

public record HabitatRs(String id, String name, String square, String climate) {
    public static HabitatRs fromEntity(HabitatEntity entity) {
        return new HabitatRs(entity.getId(), entity.getName(), entity.getSquare(), entity.getClimate());
    }
}
