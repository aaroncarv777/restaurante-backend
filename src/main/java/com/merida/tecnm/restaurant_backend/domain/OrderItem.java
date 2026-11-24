package com.merida.tecnm.restaurant_backend.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class OrderItem {

    @JsonAlias({"idPlatillo", "id"})
    private int dishId;

    @JsonAlias({"cantidad"})
    private int quantity;

    @JsonAlias({"total"})
    private double total;

    @JsonAlias({"estado"})
    private boolean active = true;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
