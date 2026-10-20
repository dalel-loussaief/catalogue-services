package com.example.tpspringmono.controllers;

import com.example.tpspringmono.dto.CategoryDTO;
import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.repos.ProduitRepo;
import com.example.tpspringmono.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProduitRepo produitRepo;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CategoryDTO dto) {
        Category saved = categoryService.save(dto.fromDto(produitRepo)).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryDTO.toDto(saved));
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        return CategoryDTO.toDto(categoryService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id,
                                                      @RequestBody @Valid CategoryDTO dto) {
        Category updated = categoryService.update(id, dto.fromDto(produitRepo)).getBody();
        return ResponseEntity.ok(CategoryDTO.toDto(updated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
    }
}