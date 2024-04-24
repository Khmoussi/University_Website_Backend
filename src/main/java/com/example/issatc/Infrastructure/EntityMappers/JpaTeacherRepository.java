package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaTeacherRepository  extends JpaRepository<TeacherMapper,String> {
   @Query("select new com.example.issatc.Entities.Responses.TeacherWithDepResponse(c.lastName ,c.firstName ,c.email , c.phoneNumb ) from teacher c  , department d where d.id = c.department.id ")
   List<TeacherWithDepResponse> getTeacherWithDep();

   @Query("select  new com.example.issatc.Infrastructure.EntityMappers.ChefDepWithDepNameResponse(c.email , d.name) from chefDepartment c , department d where c.department.id= d.id")
   List<ChefDepWithDepNameResponse> getChefDepWithDepName();
    @Query("select new com.example.issatc.Entities.Responses.TeacherWithDepResponse(c.lastName ,c.firstName ,c.email , c.phoneNumb , c.cin ) from teacher c   ")

    List<TeacherWithDepResponse> getAllTeachers();
    @Query("select new com.example.issatc.Entities.Responses.TeacherWithDepResponse(c.lastName ,c.firstName ,c.email , c.phoneNumb ,c.cin,c.department.name ) from chefDepartment c   ")

    List<TeacherWithDepResponse> getChefs();
}
