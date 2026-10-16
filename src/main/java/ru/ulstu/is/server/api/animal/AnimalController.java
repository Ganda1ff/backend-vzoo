package ru.ulstu.is.server.api.animal;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ulstu.is.server.entity.HabitatAnimalStatsProjection;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.ulstu.is.server.configuration.Constants;
import ru.ulstu.is.server.service.AnimalService;

@RestController
@RequestMapping(Constants.API_URL + AnimalController.URL)
public class AnimalController {
    public static final String URL = "/animals";
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<AnimalRs> getAll() { return animalService.getAll(); }

    @GetMapping("/{id}")
    public AnimalRs get(@PathVariable String id) { return animalService.get(id); }

    @PostMapping
    public AnimalRs create(@RequestBody @Valid AnimalRq dto) { return animalService.create(dto); }

    @PutMapping("/{id}")
    public AnimalRs update(@PathVariable String id, @RequestBody @Valid AnimalRq dto) { return animalService.update(id, dto); }

    @DeleteMapping("/{id}")
    public AnimalRs delete(@PathVariable String id) { return animalService.delete(id); }
    @GetMapping("/statistics")
    public List<HabitatAnimalStatsProjection> statistics(@RequestParam(defaultValue = "") String climate) {
        return animalService.getHabitatStatistics(climate);
    }
}
