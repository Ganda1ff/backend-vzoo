package ru.ulstu.is.server.api.animal;

import jakarta.validation.constraints.NotBlank;
import ru.ulstu.is.server.entity.AnimalEntity;
import ru.ulstu.is.server.entity.DietEntity;
import ru.ulstu.is.server.entity.HabitatEntity;

public record AnimalRq(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String image,
        @NotBlank String habitatId,
        @NotBlank String dietId) {
    public AnimalEntity toEntity(HabitatEntity habitat, DietEntity diet) {
        final AnimalEntity entity = new AnimalEntity();
        applyTo(entity, habitat, diet);
        return entity;
    }

    public void applyTo(AnimalEntity entity, HabitatEntity habitat, DietEntity diet) {
        entity.setName(name);
        entity.setDescription(description);
        entity.setImage(image);
        entity.setHabitat(habitat);
        entity.setDiet(diet);
    }
}
