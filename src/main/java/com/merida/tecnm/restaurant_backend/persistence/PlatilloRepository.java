package com.merida.tecnm.restaurant_backend.persistence;

import com.merida.tecnm.restaurant_backend.domain.Dish;
import com.merida.tecnm.restaurant_backend.domain.repository.DishRepository;
import com.merida.tecnm.restaurant_backend.persistence.crud.PlatilloCrudRepository;
import com.merida.tecnm.restaurant_backend.persistence.entity.Platillo;
import com.merida.tecnm.restaurant_backend.persistence.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlatilloRepository implements DishRepository {

    @Autowired
    private PlatilloCrudRepository platilloCrudRepository;

    @Autowired
    private DishMapper mapper;

    @Override
    public List<Dish> getAll() {
        List<Platillo> platillos = (List<Platillo>) platilloCrudRepository.findAll();
        return mapper.toDishes(platillos);
    }

    @Override
    public Optional<List<Dish>> getByCategory(int categoryId) {
        List<Platillo> platillos = platilloCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toDishes(platillos));
    }

    @Override
    public Optional<Dish> getDish(int dishId) {
        return platilloCrudRepository.findById(dishId).map(mapper::toDish);
    }

    @Override
    public Dish save(Dish dish) {
        Platillo platillo = mapper.toPlatillo(dish);
        return mapper.toDish(platilloCrudRepository.save(platillo));
    }

    @Override
    public void delete(int dishId) {
        platilloCrudRepository.deleteById(dishId);
    }
}
