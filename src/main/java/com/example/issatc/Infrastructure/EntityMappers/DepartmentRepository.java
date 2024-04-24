package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Infrastructure.JpaChefDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepartmentRepository extends JpaRepository<DepartmentMapper,Integer> {
    @Query(value = "select id from department d where d.name =:name " ,nativeQuery = true)

    int getIdByName(String name);
}
