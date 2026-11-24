package com.merida.tecnm.restaurant_backend.domain.repository;

import com.merida.tecnm.restaurant_backend.domain.Table;
import java.util.List;
import java.util.Optional;

public interface TableRepository {
    List<Table> getAll();
    Optional<Table> getTable(int tableId);
    Table save(Table table);
    void delete(int tableId);
}
