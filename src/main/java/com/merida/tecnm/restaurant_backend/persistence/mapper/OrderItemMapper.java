package com.merida.tecnm.restaurant_backend.persistence.mapper;

import com.merida.tecnm.restaurant_backend.domain.OrderItem;
import com.merida.tecnm.restaurant_backend.persistence.entity.PedidoPlatillo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {DishMapper.class})
public interface OrderItemMapper {

    @Mappings({
            @Mapping(source = "id.idPlatillo", target = "dishId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "total"),
            @Mapping(source = "estado", target = "active")
    })
    OrderItem toOrderItem(PedidoPlatillo pedidoPlatillo);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "pedido", ignore = true),
            @Mapping(target = "platillo", ignore = true),
            @Mapping(target = "id.idPedido", ignore = true)
    })
    PedidoPlatillo toPedidoPlatillo(OrderItem item);
}
