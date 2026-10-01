package ru.ulstu.is.server.entity;

public class TicketTypeEntity extends BaseEntity {
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
