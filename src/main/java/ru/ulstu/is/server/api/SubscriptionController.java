package ru.ulstu.is.server.api;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.ulstu.is.server.configuration.Constants;

@RestController
@RequestMapping(Constants.API_URL + SubscriptionController.URL)
public class SubscriptionController {
    public static final String URL = "/subscriptions";
    private final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    private final ConcurrentLinkedQueue<SubscriptionDto> subscriptions = new ConcurrentLinkedQueue<>(List.of(
            new SubscriptionDto("sub-1", "Тестовый подписчик", "demo@vzoo.local", "2026-09-17T12:00:00.000Z")));

    @GetMapping
    public List<SubscriptionDto> getAll() {
        log.debug("Get all subscriptions");
        return subscriptions.stream().toList();
    }

    @GetMapping("/{id}")
    public SubscriptionDto get(@PathVariable String id) {
        log.debug("Get subscription with id {}", id);
        return subscriptions.stream().filter(item -> item.getId().equals(id)).findAny()
                .orElseThrow(() -> new NotFoundException("Subscription", id));
    }

    @PostMapping
    public SubscriptionDto create(@RequestBody SubscriptionDto item) {
        log.debug("Create subscription for {}", item.getEmail());
        item.setId(UUID.randomUUID().toString());
        subscriptions.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public SubscriptionDto edit(@PathVariable String id, @RequestBody SubscriptionDto newItem) {
        log.debug("Edit subscription with id {}", id);
        SubscriptionDto item = get(id);
        item.setName(newItem.getName());
        item.setEmail(newItem.getEmail());
        item.setCreatedAt(newItem.getCreatedAt());
        return item;
    }

    @DeleteMapping("/{id}")
    public SubscriptionDto delete(@PathVariable String id) {
        log.debug("Delete subscription with id {}", id);
        SubscriptionDto item = get(id);
        subscriptions.remove(item);
        return item;
    }
}
