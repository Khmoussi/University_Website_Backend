package com.example.issatc.utilities;

import com.example.issatc.Infrastructure.EntityMappers.UserMapper;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
/*
public class Token {
    @Id
    @GeneratedValue
    private Integer id;

    //@Column(unique = true) my refresh and access token are generated with the same value so no
    private String token;
    private boolean expiration;
    private boolean revocation;
    private TokenType type;
    @ManyToOne
    @JoinColumn(name = "email")
    UserMapper user;
}
*/