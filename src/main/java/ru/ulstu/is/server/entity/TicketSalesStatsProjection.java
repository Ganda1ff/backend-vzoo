package ru.ulstu.is.server.entity;

public interface TicketSalesStatsProjection {
    String getTicketTitle();
    Long getOrdersCount();
    Long getTicketsSold();
    Long getRevenue();
}
