package com.padillatom.asadoremotobackend.controller;

import com.padillatom.asadoremotobackend.model.response.CategoryResponse;
import com.padillatom.asadoremotobackend.service.CategoryService;
import com.padillatom.asadoremotobackend.model.request.CategoryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category Controller")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Lista de Category",
            description = "Obtiene todas las Category.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de Category"
                    )
            }
    )
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "ADMIN - Crear Category",
            description = "Creacion de Category. Si el titulo ya existe, sera reemplazada con los nuevos valores.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Creacion de Category"
                    )
            }
    )
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "ADMIN - Update Category por ID",
            description = "Update de Category. No acepta titulos ya existentes.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update de Category"
                    )
            }
    )
    public CategoryResponse updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(
            summary = "ADMIN - Elimina una Category por ID",
            description = "Eliminacion de Category.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delete de Category"
                    )
            }
    )
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
