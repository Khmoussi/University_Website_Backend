package com.example.issatc.Entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class Departement {
    private String nom;
    private int id;

    public Departement(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }
    public Departement() {

    }
}
