package com.example.tpspringmono.services;

import com.example.tpspringmono.entities.Category;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<Category> save(Category category);
    Category findById(int id);
    ResponseEntity<Category> update(int id,Category category);
    void delete(int id);
}
