package com.example.issatc.UseCases;

import com.example.issatc.Entities.Account;
import com.example.issatc.Entities.DepartementChef;
import com.example.issatc.Entities.Requests.*;
import com.example.issatc.Entities.StudentAccount;
import com.example.issatc.Entities.TeacherAccount;
import com.example.issatc.Infrastructure.JpaChefDepartment;
import com.example.issatc.Ports.AuthenticationRepository;
import com.example.issatc.Ports.AuthenticationServicePort;


public class AuthenticationService implements AuthenticationServicePort {
    private final AuthenticationRepository authenticationRepository;
    public  AuthenticationService(AuthenticationRepository authenticationRepository){
        this.authenticationRepository=authenticationRepository;
    }

    @Override
    public int createStudentAccount(StudentAccountRequest accountRequest) {
        StudentAccount account=new StudentAccount(accountRequest.getEmail(), accountRequest.getPassword(), accountRequest.getNumTelephone(),accountRequest.getNumIsncription());
      //verify user exists and doesn't have account
        int result =this.authenticationRepository.createStudentAccountVerification(accountRequest.getEmail());
      if(result  ==1)
      {
            authenticationRepository.saveStudentAccount(account);

      }
       return result;

    }
    @Override
    public int modifyStudentAccount(StudentAccountRequest accountRequest) {
        int result= this.authenticationRepository.createStudentAccountVerification(accountRequest.getEmail());
        if(result ==2){
            StudentAccount account=new StudentAccount(accountRequest.getEmail(), accountRequest.getPassword(), accountRequest.getNumTelephone(),accountRequest.getNumIsncription());
            this.authenticationRepository.modifyStudentAccount(account);
        }
        return  result;
    }

    @Override
    public int createTeacherAccount(TeacherAccountRequest accountRequest) {
        TeacherAccount account=new TeacherAccount(accountRequest.getEmail(), accountRequest.getPassword(), accountRequest.getNumTelephone(),accountRequest.getCin());
        //verify user exists and doesn't have account
        int result =this.authenticationRepository.createTeacherAccountVerification(accountRequest.getEmail());
        if(result  ==1)
        {
            authenticationRepository.saveAccount(account);

        }
        return result;

    }
    @Override
    public int modifyTeacherAccount(TeacherAccountRequest accountRequest) {
        int result= this.authenticationRepository.createTeacherAccountVerification(accountRequest.getEmail());
        if(result ==2){
            TeacherAccount  account=new TeacherAccount(accountRequest.getEmail(), accountRequest.getPassword(), accountRequest.getNumTelephone(),accountRequest.getCin());

            this.authenticationRepository.modifyTeacherAccount(account);
        }
        return  result;    }

    @Override
    public int deleteAccount(String email) {

        return this.authenticationRepository.deleteAccount(email);
    }


    @Override
    public boolean saveUser(UserRequest userRequest) {
       if( !this.authenticationRepository.existsByEmail(userRequest.getEmail()))
        return this.authenticationRepository.save(userRequest);
        return false;

    }

    @Override
            //sending code
    public int resetPassword(String email) {
int result=this.authenticationRepository.createAccountVerification(email);
if(result==2){
    this.authenticationRepository.sendRecoveryCode(email);
}
return result;
    }

    @Override
    public boolean changePassword(NewPasswordRequest newPasswordRequest) {
      return  this.authenticationRepository.changePassword(newPasswordRequest);
    }

    @Override
    public boolean modifyTeacherAccountNoPsd(String firstName, String lastName, String email, long cin, long phoneNum) {
        if(this.authenticationRepository.hasAccount(email)) {
            return this.authenticationRepository.modifyTeacherAccountNoPsd(firstName, lastName, email, cin, phoneNum);
        }
        return false;
    }

    @Override
    public boolean modifyTeacherDep(String email, String departmentName) {
        if(this.authenticationRepository.hasAccount(email)) {
            if (departmentName != null && (departmentName.equals("") == false))

                this.authenticationRepository.modifyTeacherDep(email, departmentName);
                // delete department if exists
            else this.authenticationRepository.deleteChefDepAssignement(email);
        }
        return true;
    }


    @Override
    public Account recoverAccount(String email) {
        return authenticationRepository.recoverAccount(email);
    }

    @Override
    public int saveDepartmentChef(DepartmentChefRequest request){

    return this.authenticationRepository.saveChefDepartment(request);

    }

    @Override
    public boolean studentExistsById(String email) {
        return this.authenticationRepository.studentExistsById(email);
    }

    public void test(){
        System.out.println("test failed");
    }
}
