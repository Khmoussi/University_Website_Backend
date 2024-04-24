package com.example.issatc.Infrastructure;

import com.example.issatc.Infrastructure.EntityMappers.ChefDepartmentMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaChefDepartment extends JpaRepository<ChefDepartmentMapper,String> {

    @Modifying
@Transactional
   @Query(value = "insert into chef_department (department_id,email) values(:id , :email)",nativeQuery = true)
    int assignChef(@Param("email") String email, @Param("id") int id);

    @Query(value = "select count(*) from chef_department c where c.department_id =:departmentId  ",nativeQuery = true)
    int existsByDepId(@Param("departmentId") int departmentId);

    @Modifying
    @Transactional
    @Query(value = "delete from chef_department c where c.email =:email  ",nativeQuery = true)
    void deleteChefDepAssignement(@Param("email") String email);
}
