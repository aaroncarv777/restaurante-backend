package com.merida.tecnm.restaurant_backend.domain.service;

import com.merida.tecnm.restaurant_backend.domain.Order;
import com.merida.tecnm.restaurant_backend.domain.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<List<Order>> getByClient(String clientId) {
        return orderRepository.getByClient(clientId);
    }

    public Optional<Order> getOrder(int orderId) {
        return orderRepository.getOrder(orderId);
    }

    @Transactional
    public Order save(Order order) {
        if (order.getDate() == null) {
            order.setDate(LocalDateTime.now());
        }
        if (order.getState() == null || order.getState().isBlank()) {
            order.setState("P");
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Optional<Order> update(int orderId, Order order) {
        return getOrder(orderId).map(existing -> {
            order.setOrderId(orderId);
            if (order.getDate() == null) {
                order.setDate(existing.getDate());
            }
            if (order.getClientId() == null) {
                order.setClientId(existing.getClientId());
            }
            if (order.getTableId() == null) {
                order.setTableId(existing.getTableId());
            }
            if (order.getItems() == null || order.getItems().isEmpty()) {
                order.setItems(existing.getItems());
            }
            return orderRepository.save(order);
        });
    }

    @Transactional
    public boolean delete(int orderId) {
        return getOrder(orderId).map(order -> {
            orderRepository.delete(orderId);
            return true;
        }).orElse(false);
    }
}
