package ru.ulstu.is.server.api;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.ulstu.is.server.configuration.Constants;

@RestController
@RequestMapping(Constants.API_URL + AnimalController.URL)
public class AnimalController {
    public static final String URL = "/animals";
    private final Logger log = LoggerFactory.getLogger(AnimalController.class);

    private final ConcurrentLinkedQueue<AnimalDto> animals = new ConcurrentLinkedQueue<>(List.of(
            new AnimalDto("1", "Выхухоль",
                    "Редкое животное, занесённое в Красную книгу. Хорошо плавает и живёт рядом с водой.",
                    "/images/animal1.png", "1", "1"),
            new AnimalDto("2", "Енот",
                    "Любопытный и дружелюбный зверёк, который любит фрукты и активные игры.",
                    "/images/animal2.jpg", "2", "2"),
            new AnimalDto("3", "Горилла",
                    "Умное и сильное животное. В онлайн-зоопарке можно наблюдать за его повседневными привычками.",
                    "/images/animal3.jpg", "3", "3"),
            new AnimalDto("gzzRKM2sJwc", "лоржойцукенгш", "фывапрол", "../images/animal7.png", "1", "3")));

    @GetMapping
    public List<AnimalDto> getAll() {
        log.debug("Get all animals");
        return animals.stream().toList();
    }

    @GetMapping("/{id}")
    public AnimalDto get(@PathVariable String id) {
        log.debug("Get animal with id {}", id);
        return animals.stream().filter(item -> item.getId().equals(id)).findAny()
                .orElseThrow(() -> new NotFoundException("Animal", id));
    }

    @PostMapping
    public AnimalDto create(@RequestBody AnimalDto item) {
        log.debug("Create animal with data {}", item.getName());
        item.setId(UUID.randomUUID().toString());
        animals.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public AnimalDto edit(@PathVariable String id, @RequestBody AnimalDto newItem) {
        log.debug("Edit animal with id {}", id);
        AnimalDto item = get(id);
        item.setName(newItem.getName());
        item.setDescription(newItem.getDescription());
        item.setImage(newItem.getImage());
        item.setHabitatId(newItem.getHabitatId());
        item.setDietId(newItem.getDietId());
        return item;
    }

    @DeleteMapping("/{id}")
    public AnimalDto delete(@PathVariable String id) {
        log.debug("Delete animal with id {}", id);
        AnimalDto item = get(id);
        animals.remove(item);
        return item;
    }
}
