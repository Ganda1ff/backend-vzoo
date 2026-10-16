package ru.ulstu.is.server.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "diet")
public class DietEntity extends BaseEntity {
    @OneToMany(mappedBy = "diet", fetch = FetchType.LAZY)
    private Set<AnimalEntity> animals = new HashSet<>();

    public Set<AnimalEntity> getAnimals() {
        return animals;
    }

    public void addAnimal(AnimalEntity animal) {
        animal.setDiet(this);
    }

    public void removeAnimal(AnimalEntity animal) {
        if (animal.getDiet() == this)
            animal.setDiet(null);
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// вывести всех животных с определенным рационом питания