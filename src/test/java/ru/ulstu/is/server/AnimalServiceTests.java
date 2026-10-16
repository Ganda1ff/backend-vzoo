package ru.ulstu.is.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ru.ulstu.is.server.api.animal.AnimalRq;
import ru.ulstu.is.server.api.diet.DietRq;
import ru.ulstu.is.server.api.habitat.HabitatRq;
import ru.ulstu.is.server.repository.AnimalRepository;
import ru.ulstu.is.server.repository.DietRepository;
import ru.ulstu.is.server.repository.HabitatRepository;
import ru.ulstu.is.server.service.AnimalService;
import ru.ulstu.is.server.service.DietService;
import ru.ulstu.is.server.service.HabitatService;

@SpringBootTest
@Transactional
class AnimalServiceTests {
    @Autowired AnimalService service;
    @Autowired HabitatService habitatService;
    @Autowired DietService dietService;
    @Autowired AnimalRepository animalRepository;
    @Autowired HabitatRepository habitatRepository;
    @Autowired DietRepository dietRepository;

    @BeforeEach void reset() {
        animalRepository.deleteAll();
        habitatRepository.deleteAll();
        dietRepository.deleteAll();
    }

    @Test void crudAndRelationsTest() {
        final var habitat = habitatService.create(new HabitatRq("Лес", "100", "Умеренный"));
        final var diet = dietService.create(new DietRq("Растительная пища"));
        final var created = service.create(new AnimalRq("Олень", "Описание", "/images/deer.jpg", habitat.id(), diet.id()));
        Assertions.assertEquals(habitat.id(), created.habitat().id());
        Assertions.assertEquals(diet.id(), created.diet().id());
        final var updated = service.update(created.id(), new AnimalRq("Северный олень", "Новое описание", "/images/deer.jpg", habitat.id(), diet.id()));
        Assertions.assertEquals("Северный олень", updated.name());
        service.delete(created.id());
        Assertions.assertTrue(service.getAll().isEmpty());
    }
}
