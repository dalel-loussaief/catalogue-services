package com.example.tpspringmono.services;

import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.ProduitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepo produitRepo;

    @Override
    public ResponseEntity<Produit> save(Produit product) {
        // Création : on force un INSERT
        product.setId(null);
        Produit saved = produitRepo.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<Produit> update(int id, Produit product) {
        Produit existing = getOrThrow(id);

        existing.setReference(product.getReference());
        existing.setDescription(product.getDescription());
        existing.setPrice(product.getPrice());
        existing.setStock(product.getStock());
        existing.setCat(product.getCat());

        return ResponseEntity.ok(produitRepo.save(existing));
    }

    @Override
    public void delete(int id) {
        if (!produitRepo.existsById(id)) {
            throw notFound(id);
        }
        produitRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<Produit> findById(int id) {
        return ResponseEntity.ok(getOrThrow(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAllByCategoryId(int id) {
        return produitRepo.findByCatId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Produit> findAllByCategoryName(String name) {
        return produitRepo.findByCatNom(name);
    }

    private Produit getOrThrow(int id) {
        return produitRepo.findById(id).orElseThrow(() -> notFound(id));
    }

    private ResponseStatusException notFound(int id) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable : " + id);
    }
}