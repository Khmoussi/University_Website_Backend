package com.example.issatc.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private String email;
    private String password;
    private long numTelephone;

}
