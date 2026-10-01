package ru.ulstu.is.server.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class HabitatDto {
    @JsonProperty(access = Access.READ_ONLY)
    private String id;
    private String name;
    private String square;
    private String climate;

    public HabitatDto() {
    }

    public HabitatDto(String id, String name, String square, String climate) {
        this.id = id;
        this.name = name;
        this.square = square;
        this.climate = climate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }
}
