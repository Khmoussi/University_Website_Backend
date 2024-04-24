package com.example.issatc.Infrastructure.EntityMappers;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ChefDepWithDepNameResponse {
    private String email;
    private String departmentName;

    public ChefDepWithDepNameResponse(String email, String departmentName) {
        this.email = email;
        this.departmentName = departmentName;
    }
}
