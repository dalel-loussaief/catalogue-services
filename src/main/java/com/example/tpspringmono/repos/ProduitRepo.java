package com.example.tpspringmono.repos;

import com.example.tpspringmono.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepo extends JpaRepository<Produit, Integer> {
    List<Produit> findByReferenceContaining(String reference);

    // "cat" est le nom du champ dans Produit : cat.id / cat.nom
    List<Produit> findByCatId(int id);

    List<Produit> findByCatNom(String nom);
}