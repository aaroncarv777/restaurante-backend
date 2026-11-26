package com.merida.tecnm.restaurant_backend.web.controller;

import com.merida.tecnm.restaurant_backend.domain.Client;
import com.merida.tecnm.restaurant_backend.domain.service.ClientService;
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
@RequestMapping("/clients")
@Tag(name = "Client", description = "Operations related to restaurant clients")
@SecurityRequirement(name = "BearerAuth")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    @Operation(summary = "Get all clients", description = "Return a list of all registered clients")
    @ApiResponse(responseCode = "200", description = "Clients retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<List<Client>> getAll() {
        return new ResponseEntity<>(clientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Return a client by their document/ID")
    @ApiResponse(responseCode = "200", description = "Client found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ResponseEntity<Client> getClient(
            @Parameter(description = "Client Document ID", example = "CLI-001", required = true)
            @PathVariable("id") String clientId) {
        return clientService.getClient(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new client",
            description = "Register a new client in the database",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Client Creation Example",
                                    value = """
                                            {
                                              "clientId": "CLI-002",
                                              "name": "Maria",
                                              "lastName": "Lopez Santos",
                                              "cellphone": 9991234567,
                                              "email": "maria.lopez@email.com",
                                              "password": "secretPassword123"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Client created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid client data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<Client> save(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing client",
            description = "Update client contact information or profile",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Client Update Example",
                                    value = """
                                            {
                                              "name": "Maria Fernanda",
                                              "lastName": "Lopez Santos",
                                              "cellphone": 9998887766,
                                              "email": "mfernanda.lopez@email.com"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Client updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ResponseEntity<Client> update(
            @Parameter(description = "Client Document ID to update", example = "CLI-001", required = true)
            @PathVariable("id") String clientId,
            @RequestBody Client client) {
        return clientService.update(clientId, client)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by ID", description = "Delete a client if they exist")
    @ApiResponse(responseCode = "200", description = "Client deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Client not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Client ID to delete", example = "CLI-001", required = true)
            @PathVariable("id") String clientId) {
        if (clientService.delete(clientId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
