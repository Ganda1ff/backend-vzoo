package ru.ulstu.is.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class AnimalDto {
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    private String name;
    private String description;
    private String image;
    private String habitatId;
    private String dietId;

    public AnimalDto() {
    }

    public AnimalDto(String id, String name, String description, String image, String habitatId, String dietId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.habitatId = habitatId;
        this.dietId = dietId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHabitatId() {
        return habitatId;
    }

    public void setHabitatId(String habitatId) {
        this.habitatId = habitatId;
    }

    public String getDietId() {
        return dietId;
    }

    public void setDietId(String dietId) {
        this.dietId = dietId;
    }
}
