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
@RequestMapping(Constants.API_URL + DietController.URL)
public class DietController {
    public static final String URL = "/diets";
    private final Logger log = LoggerFactory.getLogger(DietController.class);

    private final ConcurrentLinkedQueue<DietDto> diets = new ConcurrentLinkedQueue<>(List.of(
            new DietDto("1", "Насекомые и водные беспозвоночные"),
            new DietDto("2", "Фрукты, ягоды и небольшие животные"),
            new DietDto("3", "Растительная пища"),
            new DietDto("4", "Рыба и морепродукты")));

    @GetMapping
    public List<DietDto> getAll() {
        log.debug("Get all diets");
        return diets.stream().toList();
    }

    @GetMapping("/{id}")
    public DietDto get(@PathVariable String id) {
        log.debug("Get diet with id {}", id);
        return diets.stream().filter(item -> item.getId().equals(id)).findAny()
                .orElseThrow(() -> new NotFoundException("Diet", id));
    }

    @PostMapping
    public DietDto create(@RequestBody DietDto item) {
        log.debug("Create diet");
        item.setId(UUID.randomUUID().toString());
        diets.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public DietDto edit(@PathVariable String id, @RequestBody DietDto newItem) {
        log.debug("Edit diet with id {}", id);
        DietDto item = get(id);
        item.setName(newItem.getName());
        return item;
    }

    @DeleteMapping("/{id}")
    public DietDto delete(@PathVariable String id) {
        log.debug("Delete diet with id {}", id);
        DietDto item = get(id);
        diets.remove(item);
        return item;
    }
}
