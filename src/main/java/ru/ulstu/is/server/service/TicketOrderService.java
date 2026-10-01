package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.ulstu.is.server.api.ticketorder.TicketOrderRq;
import ru.ulstu.is.server.api.ticketorder.TicketOrderRs;
import ru.ulstu.is.server.entity.TicketOrderEntity;
import ru.ulstu.is.server.entity.TicketTypeEntity;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.TicketOrderRepository;

@Service
public class TicketOrderService {
    private final TicketOrderRepository repository;
    private final TicketTypeService ticketTypeService;

    public TicketOrderService(TicketOrderRepository repository, TicketTypeService ticketTypeService) {
        this.repository = repository;
        this.ticketTypeService = ticketTypeService;
    }

    public TicketOrderEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("TicketOrder", id));
    }
    public List<TicketOrderRs> getAll() { return repository.findAll().stream().map(TicketOrderRs::fromEntity).toList(); }
    public TicketOrderRs get(String id) { return TicketOrderRs.fromEntity(getEntity(id)); }
    public TicketOrderRs create(TicketOrderRq dto) {
        final TicketTypeEntity ticketType = ticketTypeService.getEntity(dto.ticketTypeId());
        return TicketOrderRs.fromEntity(repository.save(dto.toEntity(ticketType)));
    }
    public TicketOrderRs update(String id, TicketOrderRq dto) {
        final TicketTypeEntity ticketType = ticketTypeService.getEntity(dto.ticketTypeId());
        final TicketOrderEntity entity = getEntity(id);
        dto.applyTo(entity, ticketType);
        return TicketOrderRs.fromEntity(repository.save(entity));
    }
    public TicketOrderRs delete(String id) {
        final TicketOrderEntity entity = getEntity(id);
        repository.delete(entity);
        return TicketOrderRs.fromEntity(entity);
    }
}
