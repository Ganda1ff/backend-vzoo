package ru.ulstu.is.server.api.habitat;

import java.util.List;

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
import ru.ulstu.is.server.service.HabitatService;

@RestController
@RequestMapping(Constants.API_URL + HabitatController.URL)
public class HabitatController {
    public static final String URL = "/habitats";
    private final HabitatService habitatService;

    public HabitatController(HabitatService habitatService) {
        this.habitatService = habitatService;
    }

    @GetMapping
    public List<HabitatRs> getAll() { return habitatService.getAll(); }

    @GetMapping("/{id}")
    public HabitatRs get(@PathVariable String id) { return habitatService.get(id); }

    @PostMapping
    public HabitatRs create(@RequestBody @Valid HabitatRq dto) { return habitatService.create(dto); }

    @PutMapping("/{id}")
    public HabitatRs update(@PathVariable String id, @RequestBody @Valid HabitatRq dto) { return habitatService.update(id, dto); }

    @DeleteMapping("/{id}")
    public HabitatRs delete(@PathVariable String id) { return habitatService.delete(id); }
}
