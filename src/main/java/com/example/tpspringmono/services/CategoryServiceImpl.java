package com.example.tpspringmono.services;

import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.CategoryRepo;
import com.example.tpspringmono.repos.ProduitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    private final ProduitRepo produitRepo;

    @Override
    public ResponseEntity<Category> save(Category category) {
        List<Produit> incoming = category.getProduits();

      
        category.setId(null);
        category.setProduits(new ArrayList<>());
        Category saved = categoryRepo.save(category);

        attachProduits(saved, incoming);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(int id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Catégorie introuvable : " + id));
    }

    @Override
    public ResponseEntity<Category> update(int id, Category category) {
        Category existing = findById(id);

        existing.setNom(category.getNom());
        existing.setDescription(category.getDescription());

      
        existing.getProduits().forEach(p -> p.setCat(null));
        produitRepo.saveAll(existing.getProduits());
        existing.getProduits().clear();

     
        attachProduits(existing, category.getProduits());

        return ResponseEntity.ok(categoryRepo.save(existing));
    }

    @Override
    public void delete(int id) {
        Category existing = findById(id);

        existing.getProduits().forEach(p -> p.setCat(null));
        produitRepo.saveAll(existing.getProduits());

        categoryRepo.delete(existing);
    }

 
    private void attachProduits(Category category, List<Produit> incoming) {
        if (incoming == null || incoming.isEmpty()) {
            return;
        }
        List<Integer> ids = incoming.stream().map(Produit::getId).toList();
        List<Produit> managed = produitRepo.findAllById(ids);

        managed.forEach(p -> p.setCat(category));
        produitRepo.saveAll(managed);

        category.getProduits().addAll(managed);
    }
}