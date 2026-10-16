package ru.ulstu.is.server.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "ticket_type")
public class TicketTypeEntity extends BaseEntity {
    @OneToMany(mappedBy = "ticketType", fetch = FetchType.LAZY)
    private Set<TicketOrderEntity> orders = new HashSet<>();
    public Set<TicketOrderEntity> getOrders() { return orders; }
    public void addOrder(TicketOrderEntity order) { order.setTicketType(this); }
    public void removeOrder(TicketOrderEntity order) { if (order.getTicketType() == this) order.setTicketType(null); }
    private String title;
    private Integer price;
    private String description;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
