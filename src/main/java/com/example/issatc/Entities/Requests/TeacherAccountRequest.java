package com.example.issatc.Entities.Requests;

import lombok.Data;

@Data
public class TeacherAccountRequest {
    private String email;
    private String password;
    private long numTelephone;
    private long cin;

    public TeacherAccountRequest(String email, String password, long numTelephone, long cin) {
        this.email=email;
        this.password=password;
        this.numTelephone=numTelephone;
        this.cin=cin;
    }

}
