package com.merida.tecnm.restaurant_backend.web.controller;

import com.merida.tecnm.restaurant_backend.domain.Dish;
import com.merida.tecnm.restaurant_backend.domain.service.DishService;
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
@RequestMapping("/dishes")
@Tag(name = "Dish", description = "Operations related to restaurant dishes")
@SecurityRequirement(name = "BearerAuth")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("")
    @Operation(summary = "Get all dishes", description = "Return a list of all dishes on the menu")
    @ApiResponse(responseCode = "200", description = "Dishes retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<List<Dish>> getAll() {
        return new ResponseEntity<>(dishService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get dish by ID", description = "Return a dish by its ID")
    @ApiResponse(responseCode = "200", description = "Dish found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public ResponseEntity<Dish> getDish(
            @Parameter(description = "Dish ID", example = "1", required = true)
            @PathVariable("id") int dishId) {
        return dishService.getDish(dishId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{id}")
    @Operation(summary = "Get dishes by category", description = "Return all dishes belonging to a category")
    @ApiResponse(responseCode = "200", description = "Dishes found in category")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Category not found or empty")
    public ResponseEntity<List<Dish>> getByCategory(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return dishService.getByCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new dish",
            description = "Register a new dish in the menu",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Dish Creation Example",
                                    value = """
                                            {
                                              "name": "Hamburguesa Especial Angus",
                                              "categoryId": 1,
                                              "price": 145.0,
                                              "available": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Dish created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid dish data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<Dish> save(@RequestBody Dish dish) {
        return new ResponseEntity<>(dishService.save(dish), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing dish",
            description = "Update dish price, name, category, or availability",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Dish Update Example",
                                    value = """
                                            {
                                              "name": "Hamburguesa Doble Angus con Tocino",
                                              "categoryId": 1,
                                              "price": 165.0,
                                              "available": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Dish updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public ResponseEntity<Dish> update(
            @Parameter(description = "Dish ID to update", example = "1", required = true)
            @PathVariable("id") int dishId,
            @RequestBody Dish dish) {
        return dishService.update(dishId, dish)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a dish by ID", description = "Delete a dish if it exists")
    @ApiResponse(responseCode = "200", description = "Dish deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Dish not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Dish ID to delete", example = "1", required = true)
            @PathVariable("id") int dishId) {
        if (dishService.delete(dishId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
