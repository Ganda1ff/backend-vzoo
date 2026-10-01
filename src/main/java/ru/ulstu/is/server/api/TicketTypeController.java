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
@RequestMapping(Constants.API_URL + TicketTypeController.URL)
public class TicketTypeController {
    public static final String URL = "/ticketTypes";
    private final Logger log = LoggerFactory.getLogger(TicketTypeController.class);

    private final ConcurrentLinkedQueue<TicketTypeDto> ticketTypes = new ConcurrentLinkedQueue<>(List.of(
            new TicketTypeDto("adult", "Взрослый", 600, "Для посетителей от 18 лет"),
            new TicketTypeDto("child", "Детский", 300, "Для детей от 7 до 17 лет"),
            new TicketTypeDto("pension", "Льготный", 400, "Для пенсионеров и льготных категорий")));

    @GetMapping
    public List<TicketTypeDto> getAll() {
        log.debug("Get all ticket types");
        return ticketTypes.stream().toList();
    }

    @GetMapping("/{id}")
    public TicketTypeDto get(@PathVariable String id) {
        log.debug("Get ticket type with id {}", id);
        return ticketTypes.stream().filter(item -> item.getId().equals(id)).findAny()
                .orElseThrow(() -> new NotFoundException("TicketType", id));
    }

    @PostMapping
    public TicketTypeDto create(@RequestBody TicketTypeDto item) {
        log.debug("Create ticket type");
        item.setId(UUID.randomUUID().toString());
        ticketTypes.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public TicketTypeDto edit(@PathVariable String id, @RequestBody TicketTypeDto newItem) {
        log.debug("Edit ticket type with id {}", id);
        TicketTypeDto item = get(id);
        item.setTitle(newItem.getTitle());
        item.setPrice(newItem.getPrice());
        item.setDescription(newItem.getDescription());
        return item;
    }

    @DeleteMapping("/{id}")
    public TicketTypeDto delete(@PathVariable String id) {
        log.debug("Delete ticket type with id {}", id);
        TicketTypeDto item = get(id);
        ticketTypes.remove(item);
        return item;
    }
}
