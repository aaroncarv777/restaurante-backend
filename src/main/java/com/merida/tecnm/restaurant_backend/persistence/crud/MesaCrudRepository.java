package com.merida.tecnm.restaurant_backend.persistence.crud;

import com.merida.tecnm.restaurant_backend.persistence.entity.Mesa;
import org.springframework.data.repository.CrudRepository;

public interface MesaCrudRepository extends CrudRepository<Mesa, Integer> {
}
