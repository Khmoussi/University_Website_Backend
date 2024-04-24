package com.example.issatc.Entities.Requests;

import lombok.Getter;

@Getter
public class NewPasswordRequest {
    long recoveryCode;
    String password;
    String email;
}
