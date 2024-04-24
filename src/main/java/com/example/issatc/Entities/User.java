package com.example.issatc.Entities;

import lombok.Getter;

@Getter
public class User {
    private String email;
    private String firstName;
    private String lastName;


    public User(String email, String nom, String prenom) {
        this.email = email;
        this.firstName = nom;
        this.lastName = prenom;
    }



}
