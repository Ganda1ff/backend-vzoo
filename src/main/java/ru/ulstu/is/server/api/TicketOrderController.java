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
@RequestMapping(Constants.API_URL + TicketOrderController.URL)
public class TicketOrderController {
    public static final String URL = "/ticketOrders";
    private final Logger log = LoggerFactory.getLogger(TicketOrderController.class);

    private final ConcurrentLinkedQueue<TicketOrderDto> ticketOrders = new ConcurrentLinkedQueue<>(List.of(
            new TicketOrderDto("rbRfxNpnIZo", "иван", "ivanrotov2020@gmail.com", "child", 1,
                    "2026-05-20", 300, "2026-05-19T12:07:07.741Z"),
            new TicketOrderDto("HxEfM6jOmgk", "иван", "ivanrotov2020@gmail.com", "adult", 1,
                    "2026-05-21", 600, "2026-05-19T13:51:50.002Z")));

    @GetMapping
    public List<TicketOrderDto> getAll() {
        log.debug("Get all ticket orders");
        return ticketOrders.stream().toList();
    }

    @GetMapping("/{id}")
    public TicketOrderDto get(@PathVariable String id) {
        log.debug("Get ticket order with id {}", id);
        return ticketOrders.stream().filter(item -> item.getId().equals(id)).findAny()
                .orElseThrow(() -> new NotFoundException("TicketOrder", id));
    }

    @PostMapping
    public TicketOrderDto create(@RequestBody TicketOrderDto item) {
        log.debug("Create ticket order for {}", item.getVisitorName());
        item.setId(UUID.randomUUID().toString());
        ticketOrders.add(item);
        return item;
    }

    @PutMapping("/{id}")
    public TicketOrderDto edit(@PathVariable String id, @RequestBody TicketOrderDto newItem) {
        log.debug("Edit ticket order with id {}", id);
        TicketOrderDto item = get(id);
        item.setVisitorName(newItem.getVisitorName());
        item.setEmail(newItem.getEmail());
        item.setTicketTypeId(newItem.getTicketTypeId());
        item.setQuantity(newItem.getQuantity());
        item.setVisitDate(newItem.getVisitDate());
        item.setTotal(newItem.getTotal());
        item.setCreatedAt(newItem.getCreatedAt());
        return item;
    }

    @DeleteMapping("/{id}")
    public TicketOrderDto delete(@PathVariable String id) {
        log.debug("Delete ticket order with id {}", id);
        TicketOrderDto item = get(id);
        ticketOrders.remove(item);
        return item;
    }
}
