package com.example.issatc.Entities.Requests;

import lombok.Data;

@Data
public class StudentAccountRequest {
    private String email;
    private String password;
    private long numTelephone;
    private long numIsncription;

    public StudentAccountRequest(String email, String password, long numTelephone, long numIsncription) {
        this.email = email;
        this.password = password;
        this.numTelephone = numTelephone;
        this.numIsncription = numIsncription;
    }
}
