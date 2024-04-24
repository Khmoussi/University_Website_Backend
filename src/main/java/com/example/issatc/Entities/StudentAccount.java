package com.example.issatc.Entities;

import lombok.Getter;

@Getter
public class StudentAccount extends  Account{
    long numInscription;
    public StudentAccount(String email, String password, long numTelephone,long numInscription) {
        super(email, password, numTelephone);
        this.numInscription=numInscription;
    }
}
