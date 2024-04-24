package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaDepartmentRepository extends JpaRepository<DepartmentMapper,Integer> {
    @Query("select new com.example.issatc.Entities.Departement( d.name,d.id) from department d ")
    List<Departement> getDepartments();
}
