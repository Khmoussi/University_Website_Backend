package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaDepartmentRepository extends JpaRepository<DepartmentMapper,Integer> {
    @Query("select new com.example.issatc.Entities.Departement( d.name,d.id) from department d ")
    List<Departement> getDepartments();
@Query("select count(*) from department d where d.name =:name")
    int existsByName(@Param("name") String departmentName);
}
