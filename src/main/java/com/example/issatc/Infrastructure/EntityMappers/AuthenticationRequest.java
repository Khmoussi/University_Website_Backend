package com.example.issatc.Infrastructure.EntityMappers;

import lombok.Data;

@Data

public class AuthenticationRequest {
    private String email;
    private String password;
}
