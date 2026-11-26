package com.merida.tecnm.restaurant_backend.web.controller;

import com.merida.tecnm.restaurant_backend.domain.Table;
import com.merida.tecnm.restaurant_backend.domain.service.TableService;
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
@RequestMapping("/tables")
@Tag(name = "Table", description = "Operations related to restaurant tables")
@SecurityRequirement(name = "BearerAuth")
public class TableController {

    @Autowired
    private TableService tableService;

    @GetMapping("")
    @Operation(summary = "Get all tables", description = "Return a list of all tables in the restaurant")
    @ApiResponse(responseCode = "200", description = "Tables retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<List<Table>> getAll() {
        return new ResponseEntity<>(tableService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get table by ID", description = "Return a table by its unique identifier")
    @ApiResponse(responseCode = "200", description = "Table found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Table not found")
    public ResponseEntity<Table> getTable(
            @Parameter(description = "Table ID", example = "1", required = true)
            @PathVariable("id") int tableId) {
        return tableService.getTable(tableId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new table",
            description = "Register a new table and return the created record",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Table Creation Example",
                                    value = """
                                            {
                                              "number": 5,
                                              "capacity": 4,
                                              "location": "Terraza",
                                              "status": "DISPONIBLE"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Table created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid table data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<Table> save(@RequestBody Table table) {
        return new ResponseEntity<>(tableService.save(table), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing table",
            description = "Update table capacity, location, or status by its ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Table Update Example",
                                    value = """
                                            {
                                              "number": 5,
                                              "capacity": 6,
                                              "location": "Terraza VIP",
                                              "status": "OCUPADA"
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Table updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Table not found")
    public ResponseEntity<Table> update(
            @Parameter(description = "Table ID to update", example = "1", required = true)
            @PathVariable("id") int tableId,
            @RequestBody Table table) {
        return tableService.update(tableId, table)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a table by ID", description = "Delete a table if it exists")
    @ApiResponse(responseCode = "200", description = "Table deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Table not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Table ID to delete", example = "1", required = true)
            @PathVariable("id") int tableId) {
        if (tableService.delete(tableId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
