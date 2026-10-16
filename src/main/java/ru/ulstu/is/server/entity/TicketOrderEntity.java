package ru.ulstu.is.server.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "ticket_order")
public class TicketOrderEntity extends BaseEntity {
    private String visitorName;
    private String email;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_type_id")
    private TicketTypeEntity ticketType;
    private Integer quantity;
    private String visitDate;
    private Integer total;
    private String createdAt;

    public String getVisitorName() { return visitorName; }
    public void setVisitorName(String visitorName) { this.visitorName = visitorName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public TicketTypeEntity getTicketType() { return ticketType; }
    public void setTicketType(TicketTypeEntity ticketType) {
        if (this.ticketType == ticketType) return;
        if (this.ticketType != null) this.ticketType.getOrders().remove(this);
        this.ticketType = ticketType;
        if (ticketType != null) ticketType.getOrders().add(this);
    }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
