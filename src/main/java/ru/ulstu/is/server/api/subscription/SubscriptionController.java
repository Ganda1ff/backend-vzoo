package ru.ulstu.is.server.api.subscription;

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
import ru.ulstu.is.server.service.SubscriptionService;

@RestController
@RequestMapping(Constants.API_URL + SubscriptionController.URL)
public class SubscriptionController {
    public static final String URL = "/subscriptions";
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public List<SubscriptionRs> getAll() { return subscriptionService.getAll(); }

    @GetMapping("/{id}")
    public SubscriptionRs get(@PathVariable String id) { return subscriptionService.get(id); }

    @PostMapping
    public SubscriptionRs create(@RequestBody @Valid SubscriptionRq dto) { return subscriptionService.create(dto); }

    @PutMapping("/{id}")
    public SubscriptionRs update(@PathVariable String id, @RequestBody @Valid SubscriptionRq dto) { return subscriptionService.update(id, dto); }

    @DeleteMapping("/{id}")
    public SubscriptionRs delete(@PathVariable String id) { return subscriptionService.delete(id); }
}
