package ru.ulstu.is.server.api.ticketorder;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ulstu.is.server.entity.TicketSalesStatsProjection;

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
import ru.ulstu.is.server.service.TicketOrderService;

@RestController
@RequestMapping(Constants.API_URL + TicketOrderController.URL)
public class TicketOrderController {
    public static final String URL = "/ticketOrders";
    private final TicketOrderService ticketOrderService;

    public TicketOrderController(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @GetMapping
    public List<TicketOrderRs> getAll() { return ticketOrderService.getAll(); }

    @GetMapping("/{id}")
    public TicketOrderRs get(@PathVariable String id) { return ticketOrderService.get(id); }

    @PostMapping
    public TicketOrderRs create(@RequestBody @Valid TicketOrderRq dto) { return ticketOrderService.create(dto); }

    @PutMapping("/{id}")
    public TicketOrderRs update(@PathVariable String id, @RequestBody @Valid TicketOrderRq dto) { return ticketOrderService.update(id, dto); }

    @DeleteMapping("/{id}")
    public TicketOrderRs delete(@PathVariable String id) { return ticketOrderService.delete(id); }
    @GetMapping("/statistics")
    public List<TicketSalesStatsProjection> statistics(@RequestParam(defaultValue = "0000-01-01") String fromDate) {
        return ticketOrderService.getSalesStatistics(fromDate);
    }
}
