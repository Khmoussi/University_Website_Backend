package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaStudentRepository extends JpaRepository<StudentMapper,String> {
    @Modifying
    @Transactional
    @Query("update  student u set u.password =:password , u.phoneNumb =:numTelephone  ,u.numInscription =:numInscription where u.email =:email  ")

    void saveStudentAccount(@Param("email") String email, @Param("password") String password, @Param("numTelephone")long numTelephone, @Param("numInscription")long numInscription);

    @Modifying
    @Transactional
   @Query("update student u set u.group.id =:groupId where u.numInscription =:numInscription " )
    void updateStudentGroup(@Param("numInscription") long numInscription,@Param("groupId") int groupId);
}
