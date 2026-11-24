package com.merida.tecnm.restaurant_backend.persistence.mapper;

import com.merida.tecnm.restaurant_backend.domain.Table;
import com.merida.tecnm.restaurant_backend.persistence.entity.Mesa;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {

    @Mappings({
            @Mapping(source = "idMesa", target = "tableId"),
            @Mapping(source = "numero", target = "number"),
            @Mapping(source = "capacidad", target = "capacity"),
            @Mapping(source = "ubicacion", target = "location"),
            @Mapping(source = "estado", target = "status")
    })
    Table toTable(Mesa mesa);

    List<Table> toTables(List<Mesa> mesas);

    @InheritInverseConfiguration
    @Mapping(target = "pedidos", ignore = true)
    Mesa toMesa(Table table);
}
