package com.merida.tecnm.restaurant_backend.persistence.mapper;

import com.merida.tecnm.restaurant_backend.domain.Client;
import com.merida.tecnm.restaurant_backend.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "id", target = "clientId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "apellidos", target = "lastName"),
            @Mapping(source = "celular", target = "cellphone"),
            @Mapping(source = "correoElectronico", target = "email"),
            @Mapping(source = "password", target = "password")
    })
    Client toClient(Cliente cliente);

    List<Client> toClients(List<Cliente> clientes);

    @InheritInverseConfiguration
    @Mapping(target = "pedidos", ignore = true)
    Cliente toCliente(Client client);
}
