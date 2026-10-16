package ru.ulstu.is.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import ru.ulstu.is.server.entity.HabitatAnimalStatsProjection;
import ru.ulstu.is.server.entity.AnimalEntity;

public interface AnimalRepository extends JpaRepository<AnimalEntity, String> {
    @Query("select h.name as habitatName, count(a) as animalCount " +
           "from AnimalEntity a join a.habitat h " +
           "where lower(h.climate) like lower(concat('%', :climate, '%')) " +
           "group by h.id, h.name order by count(a) desc")
    List<HabitatAnimalStatsProjection> getAnimalCountByHabitat(@Param("climate") String climate);

}
