package ru.ulstu.is.server.entity;

public class HabitatEntity extends BaseEntity {
    private String name;
    private String square;
    private String climate;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSquare() { return square; }
    public void setSquare(String square) { this.square = square; }
    public String getClimate() { return climate; }
    public void setClimate(String climate) { this.climate = climate; }
}
