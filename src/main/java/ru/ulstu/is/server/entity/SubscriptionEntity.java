package ru.ulstu.is.server.entity;

public class SubscriptionEntity extends BaseEntity {
    private String name;
    private String email;
    private String createdAt;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
