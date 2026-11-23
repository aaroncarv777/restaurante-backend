package com.merida.tecnm.restaurant_backend.persistence.crud;

import com.merida.tecnm.restaurant_backend.persistence.entity.Categoria;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaCrudRepository extends CrudRepository<Categoria, Integer> {
}
