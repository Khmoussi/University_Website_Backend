package com.example.issatc.Entities;

import lombok.Getter;

@Getter
public class TeacherAccount extends Account{
    long cin;
    public TeacherAccount(String email, String password, long numTelephone,long cin) {
        super(email, password, numTelephone);
        this.cin=cin;
    }
}
