package ru.ulstu.is.server.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.ulstu.is.server.api.subscription.SubscriptionRq;
import ru.ulstu.is.server.api.subscription.SubscriptionRs;
import ru.ulstu.is.server.entity.SubscriptionEntity;
import ru.ulstu.is.server.error.NotFoundException;
import ru.ulstu.is.server.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository) { this.repository = repository; }

    public SubscriptionEntity getEntity(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Subscription", id));
    }
    public List<SubscriptionRs> getAll() { return repository.findAll().stream().map(SubscriptionRs::fromEntity).toList(); }
    public SubscriptionRs get(String id) { return SubscriptionRs.fromEntity(getEntity(id)); }
    public SubscriptionRs create(SubscriptionRq dto) { return SubscriptionRs.fromEntity(repository.save(dto.toEntity())); }
    public SubscriptionRs update(String id, SubscriptionRq dto) {
        final SubscriptionEntity entity = getEntity(id);
        dto.applyTo(entity);
        return SubscriptionRs.fromEntity(repository.save(entity));
    }
    public SubscriptionRs delete(String id) {
        final SubscriptionEntity entity = getEntity(id);
        repository.delete(entity);
        return SubscriptionRs.fromEntity(entity);
    }
}
