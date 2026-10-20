package com.example.tpspringmono.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "produits")
@Data
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String reference;

    @Column(length = 100)
    @Size(max = 100)
    private String description;

    private double price;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "idC")
    private Category cat;
}
