package ru.ulstu.is.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ulstu.is.server.entity.HabitatEntity;

public interface HabitatRepository extends JpaRepository<HabitatEntity, String> {
}
