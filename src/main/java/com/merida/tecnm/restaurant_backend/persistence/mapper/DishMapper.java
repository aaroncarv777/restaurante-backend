package com.merida.tecnm.restaurant_backend.persistence.mapper;

import com.merida.tecnm.restaurant_backend.domain.Dish;
import com.merida.tecnm.restaurant_backend.persistence.entity.Platillo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface DishMapper {

    @Mappings({
            @Mapping(source = "idPlatillo", target = "dishId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "idCategoria", target = "categoryId"),
            @Mapping(source = "precio", target = "price"),
            @Mapping(source = "disponible", target = "available"),
            @Mapping(source = "categoria", target = "category")
    })
    Dish toDish(Platillo platillo);

    List<Dish> toDishes(List<Platillo> platillos);

    @InheritInverseConfiguration
    @Mapping(target = "pedidoPlatillos", ignore = true)
    Platillo toPlatillo(Dish dish);
}
