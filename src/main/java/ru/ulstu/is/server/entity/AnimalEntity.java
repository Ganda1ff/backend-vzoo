package ru.ulstu.is.server.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "animal")
public class AnimalEntity extends BaseEntity {
    private String name;
    @Column(length = 4096)
    private String description;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitat_id")
    private HabitatEntity habitat;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_id")
    private DietEntity diet;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public HabitatEntity getHabitat() { return habitat; }
    public void setHabitat(HabitatEntity habitat) {
        if (this.habitat == habitat) return;
        if (this.habitat != null) this.habitat.getAnimals().remove(this);
        this.habitat = habitat;
        if (habitat != null) habitat.getAnimals().add(this);
    }
    public DietEntity getDiet() { return diet; }
    public void setDiet(DietEntity diet) {
        if (this.diet == diet) return;
        if (this.diet != null) this.diet.getAnimals().remove(this);
        this.diet = diet;
        if (diet != null) diet.getAnimals().add(this);
    }
}
