package com.example.issatc.Entities.Requests;

import com.example.issatc.Infrastructure.EntityMappers.Role;
import lombok.Data;

@Data

public class UserRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Role role;
}
