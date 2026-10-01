package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.ulstu.is.server.api.tickettype.TicketTypeRq;
import ru.ulstu.is.server.api.tickettype.TicketTypeRs;
import ru.ulstu.is.server.entity.TicketTypeEntity;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.TicketTypeRepository;

@Service
public class TicketTypeService {
    private final TicketTypeRepository repository;

    public TicketTypeService(TicketTypeRepository repository) { this.repository = repository; }

    public TicketTypeEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("TicketType", id));
    }
    public List<TicketTypeRs> getAll() { return repository.findAll().stream().map(TicketTypeRs::fromEntity).toList(); }
    public TicketTypeRs get(String id) { return TicketTypeRs.fromEntity(getEntity(id)); }
    public TicketTypeRs create(TicketTypeRq dto) { return TicketTypeRs.fromEntity(repository.save(dto.toEntity())); }
    public TicketTypeRs update(String id, TicketTypeRq dto) {
        final TicketTypeEntity entity = getEntity(id);
        dto.applyTo(entity);
        return TicketTypeRs.fromEntity(repository.save(entity));
    }
    public TicketTypeRs delete(String id) {
        final TicketTypeEntity entity = getEntity(id);
        repository.delete(entity);
        return TicketTypeRs.fromEntity(entity);
    }
}
