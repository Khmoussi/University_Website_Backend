package com.example.issatc.Entities;

import lombok.Getter;

import java.sql.Date;
@Getter
public class ResetPassword {
    private String email;
    private int recoveryCode;
    private Date expirationDate;
}
