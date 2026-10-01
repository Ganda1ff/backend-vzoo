package ru.ulstu.is.server.api.tickettype;

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
import ru.ulstu.is.server.service.TicketTypeService;

@RestController
@RequestMapping(Constants.API_URL + TicketTypeController.URL)
public class TicketTypeController {
    public static final String URL = "/ticketTypes";
    private final TicketTypeService ticketTypeService;

    public TicketTypeController(TicketTypeService ticketTypeService) {
        this.ticketTypeService = ticketTypeService;
    }

    @GetMapping
    public List<TicketTypeRs> getAll() { return ticketTypeService.getAll(); }

    @GetMapping("/{id}")
    public TicketTypeRs get(@PathVariable String id) { return ticketTypeService.get(id); }

    @PostMapping
    public TicketTypeRs create(@RequestBody @Valid TicketTypeRq dto) { return ticketTypeService.create(dto); }

    @PutMapping("/{id}")
    public TicketTypeRs update(@PathVariable String id, @RequestBody @Valid TicketTypeRq dto) { return ticketTypeService.update(id, dto); }

    @DeleteMapping("/{id}")
    public TicketTypeRs delete(@PathVariable String id) { return ticketTypeService.delete(id); }
}
