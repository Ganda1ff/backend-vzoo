package ru.ulstu.is.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import ru.ulstu.is.server.entity.TicketSalesStatsProjection;
import ru.ulstu.is.server.entity.TicketOrderEntity;

public interface TicketOrderRepository extends JpaRepository<TicketOrderEntity, String> {
    @Query("select t.title as ticketTitle, count(o) as ordersCount, " +
           "sum(cast(o.quantity as long)) as ticketsSold, sum(cast(o.total as long)) as revenue " +
           "from TicketOrderEntity o join o.ticketType t " +
           "where o.visitDate >= :fromDate " +
           "group by t.id, t.title order by sum(o.total) desc")
    List<TicketSalesStatsProjection> getSalesStats(@Param("fromDate") String fromDate);

}
