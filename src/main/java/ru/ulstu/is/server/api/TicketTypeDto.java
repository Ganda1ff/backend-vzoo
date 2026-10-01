package ru.ulstu.is.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class TicketTypeDto {
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    private String title;
    private Integer price;
    private String description;

    public TicketTypeDto() {
    }

    public TicketTypeDto(String id, String title, Integer price, String description) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
