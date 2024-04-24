package com.example.issatc.Infrastructure.EntityMappers.Request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AssignChefRequest {
    String email;
    String departmentName;
}
