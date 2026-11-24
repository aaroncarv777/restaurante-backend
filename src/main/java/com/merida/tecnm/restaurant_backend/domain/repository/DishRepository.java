package com.merida.tecnm.restaurant_backend.domain.repository;

import com.merida.tecnm.restaurant_backend.domain.Dish;
import java.util.List;
import java.util.Optional;

public interface DishRepository {
    List<Dish> getAll();
    Optional<List<Dish>> getByCategory(int categoryId);
    Optional<Dish> getDish(int dishId);
    Dish save(Dish dish);
    void delete(int dishId);
}
