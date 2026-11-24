package com.merida.tecnm.restaurant_backend.domain.repository;

import com.merida.tecnm.restaurant_backend.domain.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();
    Optional<List<Order>> getByClient(String clientId);
    Optional<Order> getOrder(int orderId);
    Order save(Order order);
}
