package ru.ulstu.is.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.server.entity.TicketTypeEntity;

public interface TicketTypeRepository extends JpaRepository<TicketTypeEntity, String> {
}
