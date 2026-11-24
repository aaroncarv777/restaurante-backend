package com.merida.tecnm.restaurant_backend.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.time.LocalDateTime;
import java.util.List;

public class Order {

    @JsonAlias({"idPedido", "id"})
    private Integer orderId;

    @JsonAlias({"idCliente", "id_cliente"})
    private String clientId;

    @JsonAlias({"idMesa", "id_mesa"})
    private Integer tableId;

    @JsonAlias({"fecha"})
    private LocalDateTime date;

    @JsonAlias({"medioPago", "medio_pago"})
    private String paymentMethod;

    @JsonAlias({"comentario"})
    private String comment;

    @JsonAlias({"estado"})
    private String state;

    @JsonAlias({"platillos", "items", "pedidoPlatillos"})
    private List<OrderItem> items;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
