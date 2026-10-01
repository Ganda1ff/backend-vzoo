package ru.ulstu.is.server.entity;

public class AnimalEntity extends BaseEntity {
    private String name;
    private String description;
    private String image;
    private HabitatEntity habitat;
    private DietEntity diet;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public HabitatEntity getHabitat() { return habitat; }
    public void setHabitat(HabitatEntity habitat) { this.habitat = habitat; }
    public DietEntity getDiet() { return diet; }
    public void setDiet(DietEntity diet) { this.diet = diet; }
}
