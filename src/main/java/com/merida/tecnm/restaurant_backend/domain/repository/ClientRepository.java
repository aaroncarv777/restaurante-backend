package com.merida.tecnm.restaurant_backend.domain.repository;

import com.merida.tecnm.restaurant_backend.domain.Client;
import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getAll();
    Optional<Client> getClient(String clientId);
    Optional<Client> getByEmail(String email);
    Client save(Client client);
    void delete(String clientId);
}
