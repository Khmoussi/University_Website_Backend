package com.example.issatc.Ports;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.DepartmentChefRequest;
import com.example.issatc.Entities.Requests.NewPasswordRequest;
import com.example.issatc.Entities.Requests.UserRequest;
import org.springframework.data.repository.query.Param;

public interface AuthenticationRepository {
    boolean save(UserRequest user);
    User  findByEmail(String email);

    boolean saveAccount(Account account);
    boolean saveTeacherAccount(TeacherAccount account);
    boolean saveStudentAccount(StudentAccount account);


    boolean modifyStudentAccount(StudentAccount account);

    boolean modifyTeacherAccount(TeacherAccount account);
    boolean modifyTeacherAccountNoPsd(@Param("firstName") String firstName, String lastName,  String email, long numTelephone , long cin );

    Account recoverAccount(String email);

    boolean existsByEmail(String email);

    int createStudentAccountVerification(String email);
    int createTeacherAccountVerification(String email);

    int createAccountVerification(String email);

    int deleteAccount(String email);

    void sendRecoveryCode(String email);

    boolean changePassword(NewPasswordRequest newPasswordRequest);

    boolean modifyTeacherDep(String email, String departmentName);

    int saveChefDepartment(DepartmentChefRequest request);
    boolean hasAccount(String email);

    void deleteChefDepAssignement(String email);
}
