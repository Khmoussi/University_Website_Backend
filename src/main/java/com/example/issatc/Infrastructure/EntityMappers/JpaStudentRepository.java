package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Entities.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaStudentRepository extends JpaRepository<StudentMapper,String> {
    @Modifying
    @Transactional
    @Query("update  student u set u.password =:password , u.phoneNumb =:numTelephone  ,u.numInscription =:numInscription where u.email =:email  ")

    void saveStudentAccount(@Param("email") String email, @Param("password") String password, @Param("numTelephone")long numTelephone, @Param("numInscription")long numInscription);

    @Modifying
    @Transactional
   @Query("update student u set u.group.id =:groupId where u.numInscription =:numInscription " )
    void updateStudentGroup(@Param("numInscription") long numInscription,@Param("groupId") int groupId);

    @Query("select s from student s where s.group.id =:groupId")
    List<StudentMapper> findByGroupId(@Param("groupId") int groupId);

    @Query("select count(*) from  student s where s.sector.name =:sectorId and s.numInscription =:numInscription")
    int existsInSector(@Param("sectorId") String sectorId ,@Param("numInscription") long numInscription);
    @Query("select new com.example.issatc.Entities.Student(s.email ,s.lastName ,s.firstName ,s.numInscription,s.group.id,s.sector.name) from student s where s.sector.name =:sectorId")

    List<Student> findBySectorId(@Param("sectorId") String sectorId);
   @ Query("select g.group.id from student g where g.email =:email ")
    int getStudentGroup(@Param("email") String email);
}
