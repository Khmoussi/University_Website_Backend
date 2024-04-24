package com.example.issatc.Entities.Requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DepartmentChefRequest {
    String email;
    String departmentName;

}
