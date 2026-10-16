package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.ulstu.is.server.api.animal.AnimalRq;
import ru.ulstu.is.server.api.animal.AnimalRs;
import ru.ulstu.is.server.entity.AnimalEntity;
import ru.ulstu.is.server.entity.HabitatAnimalStatsProjection;
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

    @Transactional
    public AnimalEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Animal", id));
    }
    @Transactional
    public List<AnimalRs> getAll() { return repository.findAll().stream().map(AnimalRs::fromEntity).toList(); }
    @Transactional
    public AnimalRs get(String id) { return AnimalRs.fromEntity(getEntity(id)); }
    @Transactional
    public AnimalRs create(AnimalRq dto) {
        final HabitatEntity habitat = habitatService.getEntity(dto.habitatId());
        final DietEntity diet = dietService.getEntity(dto.dietId());
        return AnimalRs.fromEntity(repository.save(dto.toEntity(habitat, diet)));
    }
    @Transactional
    public AnimalRs update(String id, AnimalRq dto) {
        final HabitatEntity habitat = habitatService.getEntity(dto.habitatId());
        final DietEntity diet = dietService.getEntity(dto.dietId());
        final AnimalEntity entity = getEntity(id);
        dto.applyTo(entity, habitat, diet);
        return AnimalRs.fromEntity(repository.save(entity));
    }
    @Transactional
    public AnimalRs delete(String id) {
        final AnimalEntity entity = getEntity(id);
        repository.delete(entity);
        return AnimalRs.fromEntity(entity);
    }
    @Transactional(readOnly = true)
    public List<HabitatAnimalStatsProjection> getHabitatStatistics(String climate) {
        return repository.getAnimalCountByHabitat(climate);
    }
}
