package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.ulstu.is.server.api.animal.AnimalRq;
import ru.ulstu.is.server.api.animal.AnimalRs;
import ru.ulstu.is.server.entity.AnimalEntity;
import ru.ulstu.is.server.entity.DietEntity;
import ru.ulstu.is.server.entity.HabitatEntity;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.AnimalRepository;

@Service
public class AnimalService {
    private final AnimalRepository repository;
    private final HabitatService habitatService;
    private final DietService dietService;

    public AnimalService(AnimalRepository repository, HabitatService habitatService, DietService dietService) {
        this.repository = repository;
        this.habitatService = habitatService;
        this.dietService = dietService;
    }

    public AnimalEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Animal", id));
    }
    public List<AnimalRs> getAll() { return repository.findAll().stream().map(AnimalRs::fromEntity).toList(); }
    public AnimalRs get(String id) { return AnimalRs.fromEntity(getEntity(id)); }
    public AnimalRs create(AnimalRq dto) {
        final HabitatEntity habitat = habitatService.getEntity(dto.habitatId());
        final DietEntity diet = dietService.getEntity(dto.dietId());
        return AnimalRs.fromEntity(repository.save(dto.toEntity(habitat, diet)));
    }
    public AnimalRs update(String id, AnimalRq dto) {
        final HabitatEntity habitat = habitatService.getEntity(dto.habitatId());
        final DietEntity diet = dietService.getEntity(dto.dietId());
        final AnimalEntity entity = getEntity(id);
        dto.applyTo(entity, habitat, diet);
        return AnimalRs.fromEntity(repository.save(entity));
    }
    public AnimalRs delete(String id) {
        final AnimalEntity entity = getEntity(id);
        repository.delete(entity);
        return AnimalRs.fromEntity(entity);
    }
}
