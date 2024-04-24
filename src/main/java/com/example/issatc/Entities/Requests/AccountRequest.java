package com.example.issatc.Entities.Requests;

import lombok.Data;

@Data
public class AccountRequest {
    private String email;
    private String password;
    private long numTelephone;
}
