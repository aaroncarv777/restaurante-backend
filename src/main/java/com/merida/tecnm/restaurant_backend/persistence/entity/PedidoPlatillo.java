package com.merida.tecnm.restaurant_backend.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos_platillos")
public class PedidoPlatillo {

    @EmbeddedId
    private PedidoPlatilloPK id;

    private Integer cantidad;

    private Double total;

    private Boolean estado;

    @ManyToOne
    @MapsId("idPedido")
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_platillo", insertable = false, updatable = false)
    private Platillo platillo;

    public PedidoPlatilloPK getId() {
        return id;
    }

    public void setId(PedidoPlatilloPK id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Platillo getPlatillo() {
        return platillo;
    }

    public void setPlatillo(Platillo platillo) {
        this.platillo = platillo;
    }
}
