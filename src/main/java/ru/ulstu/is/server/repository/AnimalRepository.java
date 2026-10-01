package ru.ulstu.is.server.repository;

import org.springframework.stereotype.Repository;

import ru.ulstu.is.server.entity.AnimalEntity;

@Repository
public class AnimalRepository extends MapRepository<AnimalEntity> {
}
