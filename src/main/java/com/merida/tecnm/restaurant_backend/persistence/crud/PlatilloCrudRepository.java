package com.merida.tecnm.restaurant_backend.persistence.crud;

import com.merida.tecnm.restaurant_backend.persistence.entity.Platillo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlatilloCrudRepository extends CrudRepository<Platillo, Integer> {

    List<Platillo> findByIdCategoriaOrderByNombreAsc(int idCategoria);
}
