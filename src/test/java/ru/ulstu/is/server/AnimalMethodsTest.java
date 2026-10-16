package ru.ulstu.is.server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.ulstu.is.server.api.animal.AnimalRq;
import ru.ulstu.is.server.api.diet.DietRq;
import ru.ulstu.is.server.api.habitat.HabitatRq;
import ru.ulstu.is.server.service.*;
import ru.ulstu.is.server.error.NotFoundException;

@SpringBootTest
@Transactional
class AnimalMethodsTest {
    @Autowired AnimalService service;
    @Autowired HabitatService habitats;
    @Autowired DietService diets;
    private AnimalRq sample() {
        var h = habitats.create(new HabitatRq("Тропический павильон", "200", "Тропический"));
        var d = diets.create(new DietRq("Фрукты"));
        return new AnimalRq("Тукан", "Красочная птица", "/toucan.jpg", h.id(), d.id());
    }
    @Test void create() { assertNotNull(service.create(sample()).id()); }
    @Test void getEntity() { var a = service.create(sample()); assertEquals(a.id(), service.getEntity(a.id()).getId()); }
    @Test void get() { var a = service.create(sample()); assertEquals(a.id(), service.get(a.id()).id()); }
    @Test void getAll() { var a = service.create(sample()); assertTrue(service.getAll().stream().anyMatch(v -> v.id().equals(a.id()))); }
    @Test void update() { var q = sample(); var a = service.create(q); assertEquals("Тукан 2", service.update(a.id(), new AnimalRq("Тукан 2", q.description(), q.image(), q.habitatId(), q.dietId())).name()); }
    @Test void delete() { var a = service.create(sample()); service.delete(a.id()); assertThrows(NotFoundException.class, () -> service.get(a.id())); }
    @Test void getHabitatStatistics() { service.create(sample()); assertTrue(service.getHabitatStatistics("Тропический").stream().anyMatch(s -> s.getAnimalCount() >= 1)); }
    @Test void bidirectionalAssociation() { var a = service.create(sample()); var e = service.getEntity(a.id()); assertTrue(e.getHabitat().getAnimals().contains(e)); assertTrue(e.getDiet().getAnimals().contains(e)); }
}
