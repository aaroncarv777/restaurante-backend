package com.merida.tecnm.restaurant_backend.persistence;

import com.merida.tecnm.restaurant_backend.domain.Client;
import com.merida.tecnm.restaurant_backend.domain.repository.ClientRepository;
import com.merida.tecnm.restaurant_backend.persistence.crud.ClienteCrudRepository;
import com.merida.tecnm.restaurant_backend.persistence.entity.Cliente;
import com.merida.tecnm.restaurant_backend.persistence.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClienteRepository implements ClientRepository {

    @Autowired
    private ClienteCrudRepository clienteCrudRepository;

    @Autowired
    private ClientMapper mapper;

    @Override
    public List<Client> getAll() {
        List<Cliente> clientes = (List<Cliente>) clienteCrudRepository.findAll();
        return mapper.toClients(clientes);
    }

    @Override
    public Optional<Client> getClient(String clientId) {
        return clienteCrudRepository.findById(clientId).map(mapper::toClient);
    }

    @Override
    public Optional<Client> getByEmail(String email) {
        return clienteCrudRepository.findByCorreoElectronico(email).map(mapper::toClient);
    }

    @Override
    public Client save(Client client) {
        Cliente cliente = mapper.toCliente(client);
        return mapper.toClient(clienteCrudRepository.save(cliente));
    }

    @Override
    public void delete(String clientId) {
        clienteCrudRepository.deleteById(clientId);
    }
}
