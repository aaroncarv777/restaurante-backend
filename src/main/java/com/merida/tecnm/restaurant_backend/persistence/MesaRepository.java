package com.merida.tecnm.restaurant_backend.persistence;

import com.merida.tecnm.restaurant_backend.domain.Table;
import com.merida.tecnm.restaurant_backend.domain.repository.TableRepository;
import com.merida.tecnm.restaurant_backend.persistence.crud.MesaCrudRepository;
import com.merida.tecnm.restaurant_backend.persistence.entity.Mesa;
import com.merida.tecnm.restaurant_backend.persistence.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MesaRepository implements TableRepository {

    @Autowired
    private MesaCrudRepository mesaCrudRepository;

    @Autowired
    private TableMapper mapper;

    @Override
    public List<Table> getAll() {
        List<Mesa> mesas = (List<Mesa>) mesaCrudRepository.findAll();
        return mapper.toTables(mesas);
    }

    @Override
    public Optional<Table> getTable(int tableId) {
        return mesaCrudRepository.findById(tableId).map(mapper::toTable);
    }

    @Override
    public Table save(Table table) {
        Mesa mesa = mapper.toMesa(table);
        return mapper.toTable(mesaCrudRepository.save(mesa));
    }

    @Override
    public void delete(int tableId) {
        mesaCrudRepository.deleteById(tableId);
    }
}
