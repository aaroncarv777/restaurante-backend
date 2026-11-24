package com.merida.tecnm.restaurant_backend.domain.service;

import com.merida.tecnm.restaurant_backend.domain.Table;
import com.merida.tecnm.restaurant_backend.domain.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<Table> getAll() {
        return tableRepository.getAll();
    }

    public Optional<Table> getTable(int tableId) {
        return tableRepository.getTable(tableId);
    }

    public Table save(Table table) {
        return tableRepository.save(table);
    }

    public Optional<Table> update(int tableId, Table table) {
        if (tableRepository.getTable(tableId).isPresent()) {
            table.setTableId(tableId);
            return Optional.of(tableRepository.save(table));
        }
        return Optional.empty();
    }

    public boolean delete(int tableId) {
        return getTable(tableId).map(table -> {
            tableRepository.delete(tableId);
            return true;
        }).orElse(false);
    }
}
