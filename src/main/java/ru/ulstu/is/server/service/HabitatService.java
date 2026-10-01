package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.ulstu.is.server.api.habitat.HabitatRq;
import ru.ulstu.is.server.api.habitat.HabitatRs;
import ru.ulstu.is.server.entity.HabitatEntity;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.HabitatRepository;

@Service
public class HabitatService {
    private final HabitatRepository repository;

    public HabitatService(HabitatRepository repository) { this.repository = repository; }

    public HabitatEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Habitat", id));
    }
    public List<HabitatRs> getAll() { return repository.findAll().stream().map(HabitatRs::fromEntity).toList(); }
    public HabitatRs get(String id) { return HabitatRs.fromEntity(getEntity(id)); }
    public HabitatRs create(HabitatRq dto) { return HabitatRs.fromEntity(repository.save(dto.toEntity())); }
    public HabitatRs update(String id, HabitatRq dto) {
        final HabitatEntity entity = getEntity(id);
        dto.applyTo(entity);
        return HabitatRs.fromEntity(repository.save(entity));
    }
    public HabitatRs delete(String id) {
        final HabitatEntity entity = getEntity(id);
        repository.delete(entity);
        return HabitatRs.fromEntity(entity);
    }
}
