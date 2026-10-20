package com.example.tpspringmono.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nom;

    private String description;

    @OneToMany(mappedBy = "cat")
    private List<Produit> produits= new ArrayList<Produit>();
}
