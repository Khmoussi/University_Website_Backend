package com.example.issatc.Infrastructure.EntityMappers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private String firstname;
    private String lastname;
    private String email;
    private String roleName;
    private String accessToken;
    private String refreshToken;
}
