package com.merida.tecnm.restaurant_backend.persistence.crud;

import com.merida.tecnm.restaurant_backend.persistence.entity.Pedido;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoCrudRepository extends CrudRepository<Pedido, Integer> {

    Optional<List<Pedido>> findByIdCliente(String idCliente);
}
