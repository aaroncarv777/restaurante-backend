package com.merida.tecnm.restaurant_backend.persistence.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "platillos")
public class Platillo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_platillo")
    private Integer idPlatillo;

    private String nombre;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    private Double precio;

    private Boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "platillo")
    private List<PedidoPlatillo> pedidoPlatillos;

    public Integer getIdPlatillo() {
        return idPlatillo;
    }

    public void setIdPlatillo(Integer idPlatillo) {
        this.idPlatillo = idPlatillo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<PedidoPlatillo> getPedidoPlatillos() {
        return pedidoPlatillos;
    }

    public void setPedidoPlatillos(List<PedidoPlatillo> pedidoPlatillos) {
        this.pedidoPlatillos = pedidoPlatillos;
    }
}
