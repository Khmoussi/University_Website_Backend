package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "chefDepartment")
public class ChefDepartmentMapper extends TeacherMapper{

    @OneToOne
    @JoinColumn(name = "departmentId" ,referencedColumnName = "id")
    private DepartmentMapper department;


}
