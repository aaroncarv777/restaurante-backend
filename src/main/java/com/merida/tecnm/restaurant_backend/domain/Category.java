package com.merida.tecnm.restaurant_backend.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Category {

    @JsonAlias({"idCategoria", "id"})
    private Integer categoryId;

    @JsonAlias({"descripcion", "nombre", "description"})
    private String category;

    @JsonAlias({"estado"})
    private boolean active;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
