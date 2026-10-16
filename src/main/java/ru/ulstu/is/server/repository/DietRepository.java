package ru.ulstu.is.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import ru.ulstu.is.server.entity.DietEntity;
import ru.ulstu.is.server.entity.DietAnimalStatsProjection;

public interface DietRepository extends JpaRepository<DietEntity, String> {
    @Query("select d.name as dietName, a.name as animalName " +
            "from AnimalEntity a join a.diet d " +
            "where lower(d.name) like lower(concat('%', :dietName, '%')) " +
            "order by d.name, a.name")
    List<DietAnimalStatsProjection> getAnimalsByDiet(@Param("dietName") String dietName);

}
