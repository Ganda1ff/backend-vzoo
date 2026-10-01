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
@RequestMapping(Constants.API_URL + HabitatController.URL)
public class HabitatController {
    public static final String URL = "/habitats";
    private final Logger log = LoggerFactory.getLogger(HabitatController.class);

    private final ConcurrentLinkedQueue<HabitatDto> habitats = new ConcurrentLinkedQueue<>(List.of(
            new HabitatDto("1", "Вольер редких животных", "320", "Редкий"),
            new HabitatDto("2", "Лесная зона", "440", "Умеренный"),
            new HabitatDto("3", "Тропический павильон", "210", "Тропический"),
            new HabitatDto("4", "Водная зона", "100", "Водный")));

    @GetMapping
    public List<HabitatDto> getAll() {
        log.debug("Get all habitats");
        return habitats.stream().toList();
    }

    @GetMapping("/{id}")
    public HabitatDto get(@PathVariable String id) {
        log.debug("Get habitat with id {}", id);
        return habitats.stream().filter(item -> item.getId().equals(id)).findAny()
                .orElseThrow(() -> new NotFoundException("Habitat", id));
    }

    @PostMapping
    public HabitatDto create(@RequestBody HabitatDto item) {
        log.debug("Create habitat");
        item.setId(UUID.randomUUID().toString());
        habitats.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public HabitatDto edit(@PathVariable String id, @RequestBody HabitatDto newItem) {
        log.debug("Edit habitat with id {}", id);
        HabitatDto item = get(id);
        item.setName(newItem.getName());
        item.setSquare(newItem.getSquare());
        item.setClimate(newItem.getClimate());
        return item;
    }

    @DeleteMapping("/{id}")
    public HabitatDto delete(@PathVariable String id) {
        log.debug("Delete habitat with id {}", id);
        HabitatDto item = get(id);
        habitats.remove(item);
        return item;
    }
}
