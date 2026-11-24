package com.merida.tecnm.restaurant_backend.domain.service;

import com.merida.tecnm.restaurant_backend.domain.Dish;
import com.merida.tecnm.restaurant_backend.domain.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAll() {
        return dishRepository.getAll();
    }

    public Optional<Dish> getDish(int dishId) {
        return dishRepository.getDish(dishId);
    }

    public Optional<List<Dish>> getByCategory(int categoryId) {
        return dishRepository.getByCategory(categoryId);
    }

    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    public Optional<Dish> update(int dishId, Dish dish) {
        if (dishRepository.getDish(dishId).isPresent()) {
            dish.setDishId(dishId);
            return Optional.of(dishRepository.save(dish));
        }
        return Optional.empty();
    }

    public boolean delete(int dishId) {
        return getDish(dishId).map(dish -> {
            dishRepository.delete(dishId);
            return true;
        }).orElse(false);
    }
}
