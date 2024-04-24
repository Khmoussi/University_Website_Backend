package com.example.issatc.Infrastructure;

import com.example.issatc.Infrastructure.EntityMappers.ResetPasswordMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaResetPasswordRepository extends JpaRepository<ResetPasswordMapper,String> {

    @Query("select count(*) from reset_password p where p.email =:email and p.recoveryCode =:recoveryCode and p.expirationDate >:currentTimeMillis")
    int verifyCode(@Param("email") String email, @Param("recoveryCode") long recoveryCode, @Param("currentTimeMillis") long currentTimeMillis);

}
