package com.example.tpspringmono.dto;

import com.example.tpspringmono.entities.Category;
import com.example.tpspringmono.entities.Produit;
import com.example.tpspringmono.repos.ProduitRepo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CategoryDTO(int id, @NotBlank String nom, @Size(max = 100) String description, List<Integer> produits) {


    static public CategoryDTO toDto(Category category) {
        List<Integer>  ids = category.getProduits().stream().map(p -> p.getId()).toList();
        CategoryDTO cat = new CategoryDTO(category.getId(),category.
                getNom(),category.getDescription(),ids);
        return cat;
    }


    public  Category fromDto(  ProduitRepo produitRepo) {
        Category category = new Category();
        category.setId(id);
        category.setNom(nom);
        category.setDescription(description);

        List<Produit> listeP = produitRepo.findAllById(produits());
        listeP.forEach(p-> p.setCat(category));
        category.setProduits(listeP);
        return category;
    }
}
