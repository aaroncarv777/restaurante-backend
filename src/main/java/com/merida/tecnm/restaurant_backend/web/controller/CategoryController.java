package com.merida.tecnm.restaurant_backend.web.controller;

import com.merida.tecnm.restaurant_backend.domain.Category;
import com.merida.tecnm.restaurant_backend.domain.service.CategoryService;
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
@RequestMapping("/categories")
@Tag(name = "Category", description = "Operations related to menu categories")
@SecurityRequirement(name = "BearerAuth")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    @Operation(summary = "Get all categories", description = "Return a list of all dish categories")
    @ApiResponse(responseCode = "200", description = "Categories retrieved successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category by ID", description = "Return a category by its ID")
    @ApiResponse(responseCode = "200", description = "Category found")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> getCategory(
            @Parameter(description = "Category ID", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        return categoryService.getCategory(categoryId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    @Operation(
            summary = "Create a new category",
            description = "Register a new category in the menu",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Category Creation Example",
                                    value = """
                                            {
                                              "category": "Postres y Cafetería",
                                              "active": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "201", description = "Category created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid category data")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update an existing category",
            description = "Update category name or status by its ID",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            examples = @ExampleObject(
                                    name = "Category Update Example",
                                    value = """
                                            {
                                              "category": "Postres Gourmet",
                                              "active": true
                                            }
                                            """
                            )
                    )
            )
    )
    @ApiResponse(responseCode = "200", description = "Category updated successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Category> update(
            @Parameter(description = "Category ID to update", example = "1", required = true)
            @PathVariable("id") int categoryId,
            @RequestBody Category category) {
        return categoryService.update(categoryId, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category by ID", description = "Delete a category if it exists")
    @ApiResponse(responseCode = "200", description = "Category deleted successfully")
    @ApiResponse(responseCode = "401", description = "Unauthorized - Valid JWT token required")
    @ApiResponse(responseCode = "403", description = "Forbidden - Access denied")
    @ApiResponse(responseCode = "404", description = "Category not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Category ID to delete", example = "1", required = true)
            @PathVariable("id") int categoryId) {
        if (categoryService.delete(categoryId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
