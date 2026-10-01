package ru.ulstu.is.server.entity;

public class TicketOrderEntity extends BaseEntity {
    private String visitorName;
    private String email;
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
    public void setTicketType(TicketTypeEntity ticketType) { this.ticketType = ticketType; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getVisitDate() { return visitDate; }
    public void setVisitDate(String visitDate) { this.visitDate = visitDate; }
    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
