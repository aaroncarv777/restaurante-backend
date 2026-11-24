package com.merida.tecnm.restaurant_backend.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Table {

    @JsonAlias({"idMesa", "id"})
    private Integer tableId;

    @JsonAlias({"numero"})
    private Integer number;

    @JsonAlias({"capacidad"})
    private Integer capacity;

    @JsonAlias({"ubicacion"})
    private String location;

    @JsonAlias({"estado"})
    private String status;

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
