package com.merida.tecnm.restaurant_backend.web.controller;

import com.merida.tecnm.restaurant_backend.domain.Order;
import com.merida.tecnm.restaurant_backend.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order", description = "Transactional restaurant order management (Master-Detail)")
@SecurityRequirement(name = "BearerAuth")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("")
    @Operation(summary = "Get all orders", description = "Return a list of all registered orders with their dish items")
    @ApiResponse(responseCode = "200", description = "Orders retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<List<Order>> getAll() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get order by ID", description = "Return an order header and its items by order ID")
    @ApiResponse(responseCode = "200", description = "Order found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Order> getOrder(
            @Parameter(description = "Order ID", example = "1", required = true)
            @PathVariable("id") int orderId) {
        return orderService.getOrder(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get orders by client ID", description = "Return all orders placed by a specific client")
    @ApiResponse(responseCode = "200", description = "Orders found for the client")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Orders not found for the client")
    public ResponseEntity<List<Order>> getByClient(
            @Parameter(description = "Client Document ID", example = "CLI-001", required = true)
            @PathVariable("clientId") String clientId) {
        return orderService.getByClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new order (Master-Detail)",
            description = "Register a new order header along with its dish items in a single atomic transaction using Cascade persist",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Master-Detail Order Example (Ticket de Restaurante)",
                                    value = """
                                            {
                                              "clientId": "CLI-001",
                                              "tableId": 1,
                                              "paymentMethod": "TARJETA",
                                              "comment": "Sin cebolla en la hamburguesa y aderezo aparte",
                                              "state": "P",
                                              "items": [
                                                {
                                                  "dishId": 1,
                                                  "quantity": 2,
                                                  "total": 170.0,
                                                  "active": true
                                                },
                                                {
                                                  "dishId": 3,
                                                  "quantity": 1,
                                                  "total": 45.0,
                                                  "active": true
                                                }
                                              ]
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Order and items created successfully via cascade")
    @ApiResponse(responseCode = "400", description = "Invalid order or detail items data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<Order> save(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.save(order), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing order",
            description = "Update order payment method, state, comment or detail items",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Order Update Example",
                                    value = """
                                            {
                                              "clientId": "CLI-001",
                                              "tableId": 1,
                                              "paymentMethod": "EFECTIVO",
                                              "comment": "Pedido entregado a la mesa",
                                              "state": "E"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Order updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Order> update(
            @Parameter(description = "Order ID to update", example = "1", required = true)
            @PathVariable("id") int orderId,
            @RequestBody Order order) {
        return orderService.update(orderId, order)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an order by ID", description = "Delete an order and all its detail items cascade-wise")
    @ApiResponse(responseCode = "200", description = "Order and items deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Order not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Order ID to delete", example = "1", required = true)
            @PathVariable("id") int orderId) {
        if (orderService.delete(orderId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
