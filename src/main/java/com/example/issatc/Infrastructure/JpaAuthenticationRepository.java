package com.example.issatc.Infrastructure;

import com.example.issatc.Infrastructure.EntityMappers.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaAuthenticationRepository extends JpaRepository<UserMapper,String> {
   @Modifying
   @Transactional
   @Query("update  user u set u.password =:password , u.phoneNumb =:numTelephone where u.email =:email  ")

   void saveAccount(@Param("email") String email, @Param("password") String password, @Param("numTelephone")long numTelephone);

   @Modifying
   @Transactional
   @Query("update user u set u.password = null , u.phoneNumb = 0 where u.email =:email ")
   int deleteAccount(@Param("email") String email);

   @Modifying
   @Transactional
   @Query("update  user u set u.lastName =:lastName , u.firstName =:firstName , u.phoneNumb =:numTelephone ,u.cin =:cin  where u.email =:email  ")

   int modifyTeacherAccountNoPsd(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("email") String email,  @Param("numTelephone")long numTelephone ,@Param("cin") long cin );

   @Query(value = " select sub.result from ( select case when email =:email and password is NULL then 1 " +
           "when email =:email and password is not NULL then 2 " +
           "else 3 " +
           "end as result " +
           "from user ) as sub   " +
           "where 1  in (sub.result) or 2 in (sub.result) ;"
           , nativeQuery = true)
   int createAccountVerification(@Param("email") String email);


   @Modifying
   @Transactional
   @Query("update user u set u.password =:password where u.email =:email")
    int updatePassword(@Param("email") String email,@Param("password") String password);


   @Modifying
   @Transactional
   @Query(value = "update chef_department c set c.department_id =:departmentId  where c.email=:email",nativeQuery = true)
   int modifyTeacherDep(@Param("email") String email,@Param("departmentId") int departmentId);

   @Query("select count(*) from user where email =:email and password is not null ")
    int hasAccount(@Param("email") String email);
}
