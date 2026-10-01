package ru.ulstu.is.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class TicketOrderDto {
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    private String visitorName;
    private String email;
    private String ticketTypeId;
    private Integer quantity;
    private String visitDate;
    private Integer total;
    private String createdAt;

    public TicketOrderDto() {
    }

    public TicketOrderDto(String id, String visitorName, String email, String ticketTypeId,
            Integer quantity, String visitDate, Integer total, String createdAt) {
        this.id = id;
        this.visitorName = visitorName;
        this.email = email;
        this.ticketTypeId = ticketTypeId;
        this.quantity = quantity;
        this.visitDate = visitDate;
        this.total = total;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
