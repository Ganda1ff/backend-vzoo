package ru.ulstu.is.server.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "habitat")
public class HabitatEntity extends BaseEntity {
    @OneToMany(mappedBy = "habitat", fetch = FetchType.LAZY)
    private Set<AnimalEntity> animals = new HashSet<>();
    public Set<AnimalEntity> getAnimals() { return animals; }
    public void addAnimal(AnimalEntity animal) { animal.setHabitat(this); }
    public void removeAnimal(AnimalEntity animal) { if (animal.getHabitat() == this) animal.setHabitat(null); }
    private String name;
    private String square;
    private String climate;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSquare() { return square; }
    public void setSquare(String square) { this.square = square; }
    public String getClimate() { return climate; }
    public void setClimate(String climate) { this.climate = climate; }
}
