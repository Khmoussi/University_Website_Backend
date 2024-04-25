package com.example.issatc.Ports;

import com.example.issatc.Entities.Account;
import com.example.issatc.Entities.DepartementChef;
import com.example.issatc.Entities.Requests.*;

public interface AuthenticationServicePort {
    int createStudentAccount(StudentAccountRequest accountRequest);
    int modifyStudentAccount(StudentAccountRequest accountRequest);
    int createTeacherAccount(TeacherAccountRequest accountRequest);
    int modifyTeacherAccount(TeacherAccountRequest accountRequest);

    int deleteAccount(String email);
    Account recoverAccount(String email);
    boolean saveUser(UserRequest userRequest);


    int resetPassword(String email);
    boolean changePassword(NewPasswordRequest newPasswordRequest);

    boolean modifyTeacherAccountNoPsd(String firstName, String lastName, String email, long cin, long phoneNum);

    boolean modifyTeacherDep(String email, String departmentName);

    int saveDepartmentChef(DepartmentChefRequest request);

    boolean studentExistsById(String email);
}
