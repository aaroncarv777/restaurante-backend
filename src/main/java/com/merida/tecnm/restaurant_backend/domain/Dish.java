package com.merida.tecnm.restaurant_backend.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Dish {

    @JsonAlias({"idPlatillo", "id"})
    private Integer dishId;

    @JsonAlias({"nombre"})
    private String name;

    @JsonAlias({"idCategoria"})
    private Integer categoryId;

    @JsonAlias({"precio"})
    private Double price;

    @JsonAlias({"disponible"})
    private Boolean available;

    private Category category;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
