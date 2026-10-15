package ru.ulstu.is.server.api.animal;

import jakarta.validation.constraints.NotBlank;
import ru.ulstu.is.server.entity.AnimalEntity;
import ru.ulstu.is.server.entity.DietEntity;
import ru.ulstu.is.server.entity.HabitatEntity;
import jakarta.validation.constraints.Size;

public record AnimalRq(
        @NotBlank @Size(min = 2, max = 20) String name,
        @NotBlank @Size(min = 10, max = 100) String description,
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
