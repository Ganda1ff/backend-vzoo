package ru.ulstu.is.server.api.animal;

import ru.ulstu.is.server.api.diet.DietRs;
import ru.ulstu.is.server.api.habitat.HabitatRs;
import ru.ulstu.is.server.entity.AnimalEntity;

public record AnimalRs(
        String id,
        String name,
        String description,
        String image,
        String habitatId,
        String dietId,
        HabitatRs habitat,
        DietRs diet) {
    public static AnimalRs fromEntity(AnimalEntity entity) {
        final HabitatRs habitat = HabitatRs.fromEntity(entity.getHabitat());
        final DietRs diet = DietRs.fromEntity(entity.getDiet());
        return new AnimalRs(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImage(),
                entity.getHabitat().getId(),
                entity.getDiet().getId(),
                habitat,
                diet);
    }
}
