package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import java.sql.Date;

@Entity(name = "reset_password")
@AllArgsConstructor
public class ResetPasswordMapper {
    public ResetPasswordMapper (){

    }
    @Id
    private String email;
    private int recoveryCode;
    private long expirationDate;
}
