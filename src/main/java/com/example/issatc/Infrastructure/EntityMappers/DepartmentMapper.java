package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "department")
public class DepartmentMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name" ,unique = true)
    private String name;


    @OneToOne(mappedBy = "department")

    private ChefDepartmentMapper chefDepartment;

    public DepartmentMapper(String name) {
        this.name = name;
    }
    public DepartmentMapper() {
    }
}
