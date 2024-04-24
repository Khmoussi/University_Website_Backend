package com.example.issatc.Infrastructure.EntityMappers;

import lombok.Data;

@Data
public class NewPasswordRequestMapper {
    long recoveryCode;
    String password;
    String email;
}
