package com.merida.tecnm.restaurant_backend.persistence.mapper;

import com.merida.tecnm.restaurant_backend.domain.Order;
import com.merida.tecnm.restaurant_backend.persistence.entity.Pedido;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mappings({
            @Mapping(source = "idPedido", target = "orderId"),
            @Mapping(source = "idCliente", target = "clientId"),
            @Mapping(source = "idMesa", target = "tableId"),
            @Mapping(source = "fecha", target = "date"),
            @Mapping(source = "medioPago", target = "paymentMethod"),
            @Mapping(source = "comentario", target = "comment"),
            @Mapping(source = "estado", target = "state"),
            @Mapping(source = "platillos", target = "items")
    })
    Order toOrder(Pedido pedido);

    List<Order> toOrders(List<Pedido> pedidos);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "cliente", ignore = true),
            @Mapping(target = "mesa", ignore = true)
    })
    Pedido toPedido(Order order);
}
