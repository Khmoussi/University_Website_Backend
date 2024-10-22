package com.example.issatc.Infrastructure.EntityMappers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {

    private String email;
    private String firstName;
    private String Lastname;

    private String roleName;

    private String accessToken;
    private String refreshToken;
}
