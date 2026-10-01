package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.ulstu.is.server.api.diet.DietRq;
import ru.ulstu.is.server.api.diet.DietRs;
import ru.ulstu.is.server.entity.DietEntity;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.DietRepository;

@Service
public class DietService {
    private final DietRepository repository;

    public DietService(DietRepository repository) { this.repository = repository; }

    public DietEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Diet", id));
    }
    public List<DietRs> getAll() { return repository.findAll().stream().map(DietRs::fromEntity).toList(); }
    public DietRs get(String id) { return DietRs.fromEntity(getEntity(id)); }
    public DietRs create(DietRq dto) { return DietRs.fromEntity(repository.save(dto.toEntity())); }
    public DietRs update(String id, DietRq dto) {
        final DietEntity entity = getEntity(id);
        dto.applyTo(entity);
        return DietRs.fromEntity(repository.save(entity));
    }
    public DietRs delete(String id) {
        final DietEntity entity = getEntity(id);
        repository.delete(entity);
        return DietRs.fromEntity(entity);
    }
}
