package ru.ulstu.is.server.api.diet;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ulstu.is.server.entity.DietAnimalStatsProjection;

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
import ru.ulstu.is.server.service.DietService;

@RestController
@RequestMapping(Constants.API_URL + DietController.URL)
public class DietController {
    public static final String URL = "/diets";
    private final DietService dietService;

    public DietController(DietService dietService) {
        this.dietService = dietService;
    }

    @GetMapping
    public List<DietRs> getAll() {
        return dietService.getAll();
    }

    @GetMapping("/{id}")
    public DietRs get(@PathVariable String id) {
        return dietService.get(id);
    }

    @PostMapping
    public DietRs create(@RequestBody @Valid DietRq dto) {
        return dietService.create(dto);
    }

    @PutMapping("/{id}")
    public DietRs update(@PathVariable String id, @RequestBody @Valid DietRq dto) {
        return dietService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public DietRs delete(@PathVariable String id) {
        return dietService.delete(id);
    }

    @GetMapping("/statistics")
    public List<DietAnimalStatsProjection> statistics(@RequestParam(defaultValue = "") String dietName) {
        return dietService.getDietStatistics(dietName);
    }
}
