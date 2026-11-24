package com.merida.tecnm.restaurant_backend.persistence;

import com.merida.tecnm.restaurant_backend.domain.Order;
import com.merida.tecnm.restaurant_backend.domain.repository.OrderRepository;
import com.merida.tecnm.restaurant_backend.persistence.crud.PedidoCrudRepository;
import com.merida.tecnm.restaurant_backend.persistence.entity.Pedido;
import com.merida.tecnm.restaurant_backend.persistence.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PedidoRepository implements OrderRepository {

    @Autowired
    private PedidoCrudRepository pedidoCrudRepository;

    @Autowired
    private OrderMapper mapper;

    @Override
    public List<Order> getAll() {
        List<Pedido> pedidos = (List<Pedido>) pedidoCrudRepository.findAll();
        return mapper.toOrders(pedidos);
    }

    @Override
    public Optional<List<Order>> getByClient(String clientId) {
        return pedidoCrudRepository.findByIdCliente(clientId)
                .map(mapper::toOrders);
    }

    @Override
    public Optional<Order> getOrder(int orderId) {
        return pedidoCrudRepository.findById(orderId).map(mapper::toOrder);
    }

    @Override
    public Order save(Order order) {
        Pedido pedido = mapper.toPedido(order);
        if (pedido.getPlatillos() != null) {
            pedido.getPlatillos().forEach(platillo -> platillo.setPedido(pedido));
        }
        return mapper.toOrder(pedidoCrudRepository.save(pedido));
    }
}
