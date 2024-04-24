package com.example.issatc.Infrastructure;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.DepartmentChefRequest;
import com.example.issatc.Entities.Requests.NewPasswordRequest;
import com.example.issatc.Entities.Requests.UserRequest;
import com.example.issatc.Infrastructure.EntityMappers.*;
import com.example.issatc.Ports.AuthenticationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class AuthenticationRepositoryImpl implements AuthenticationRepository {
    private long expiration=5*60*1000;

    public final JpaAuthenticationRepository authenticationRepository;
    public final JpaResetPasswordRepository resetPasswordRepository;
    private final PasswordEncoder passwordEncoder;
    private final JpaStudentRepository authenticationStudentRepository;
    private final JpaTeacherRepository authenticationTeacherRepository;
    private final JpaChefDepartment chefDepartmentRepository;
    private final DepartmentRepository departmentRepository;
    private final JpaSectorRepository sectorRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Override
    public boolean save(UserRequest user) {
        try {
            authenticationRepository.save(new UserMapper(user.getEmail(), user.getFirstName(), user.getLastName(),user.getRole()));
            return true;
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public boolean saveAccount(Account account) {
try{
    this.authenticationRepository.saveAccount(account.getEmail(),passwordEncoder.encode(account.getPassword()),account.getNumTelephone());
    return true;

} catch(IllegalArgumentException e){
    e.printStackTrace();
    return false;
}
    }

    @Override
    public boolean saveTeacherAccount(TeacherAccount account) {
        try{
            this.authenticationRepository.saveAccount(account.getEmail(),passwordEncoder.encode(account.getPassword()),account.getNumTelephone());
            return true;

        } catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }    }

    @Override
    public boolean saveStudentAccount(StudentAccount account) {
        try{
            this.authenticationRepository.saveAccount(account.getEmail(),passwordEncoder.encode(account.getPassword()),account.getNumTelephone());
          // this.authenticationRepository.saveStudentAccount(account.getEmail(),passwordEncoder.encode(account.getPassword()),account.getNumTelephone(),account.getNumInscription());
            return true;

        } catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }    }
    @Override
    public boolean modifyStudentAccount(StudentAccount account) {
        try{
            //this.authenticationRepository.saveAccount(account.getEmail(),passwordEncoder.encode(account.getPassword()),account.getNumTelephone());
            StudentMapper student = authenticationStudentRepository.findById(account.getEmail()).orElseThrow();
            student.setPhoneNumb(account.getNumTelephone());
            student.setNumInscription(account.getNumInscription());
            student.setEmail(account.getEmail());
            student.setPassword(passwordEncoder.encode(account.getPassword()));
            this.authenticationStudentRepository.save(student);
            return true;

        } catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }    }

    @Override
    public boolean modifyTeacherAccount(TeacherAccount account) {
        try{
            //this.authenticationRepository.saveAccount(account.getEmail(),passwordEncoder.encode(account.getPassword()),account.getNumTelephone());
            TeacherMapper teacher = authenticationTeacherRepository.findById(account.getEmail()).orElseThrow();
            teacher.setPhoneNumb(account.getNumTelephone());
            teacher.setCin(account.getCin());
            teacher.setEmail(account.getEmail());
            teacher.setPassword(passwordEncoder.encode(account.getPassword()));
            this.authenticationTeacherRepository.save(teacher);
            return true;

        } catch(IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }    }

    @Override
    public boolean modifyTeacherAccountNoPsd(String firstName, String lastName, String email, long numTelephone, long cin) {
       if( this.authenticationRepository.modifyTeacherAccountNoPsd(firstName,lastName,email,numTelephone,cin)>0)
           return true;
       return false;
    }
    @Override
    public boolean modifyTeacherDep(String email, String departmentName) {
        int departmentId;
             departmentId= this.departmentRepository.getIdByName(departmentName);
        if(this.chefDepartmentRepository.existsByDepId(departmentId)==0 && this.chefDepartmentRepository.existsById(email) )
        return  this.authenticationRepository.modifyTeacherDep(email,departmentId)>=1;
        if(this.chefDepartmentRepository.existsByDepId(departmentId)==0 )
        return (this.chefDepartmentRepository.assignChef(email,departmentId)>=1);
        return false;
    }

    @Override
    public int saveChefDepartment(DepartmentChefRequest request) {

        if(this.authenticationRepository.hasAccount(request.getEmail())>0) {

            int id = this.departmentRepository.getIdByName(request.getDepartmentName());
            if (this.authenticationTeacherRepository.existsById(request.getEmail()))
             return    this.chefDepartmentRepository.assignChef(request.getEmail(), id);
        }
        return 0;
    }

    @Override
    public boolean hasAccount(String email) {
        int result=this.authenticationRepository.hasAccount(email);
if(result>0)
    return true;
    return false;
    }

    @Override
    public void deleteChefDepAssignement(String email) {
        this.chefDepartmentRepository.deleteChefDepAssignement(email);
    }


    @Override
    public Account recoverAccount(String email) {
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
       return this.authenticationRepository.existsById(email);
    }

    @Override
    public int createStudentAccountVerification(String email) {
        return createAccountVerification(email);
    }

    @Override
    public int createTeacherAccountVerification(String email) {
        return createAccountVerification(email);
    }
@Override
    public int createAccountVerification(String email) {
try {
        return this.authenticationRepository.createAccountVerification(email);
}catch (Exception e){
    e.printStackTrace();
    return 0;

}
    }

    @Override
    public int deleteAccount(String email) {
       int result =this.authenticationRepository.deleteAccount(email);
        return  result;
    }

    @Override
    public void sendRecoveryCode(String email) {
        int code=generateRecoveryCode();
        this.resetPasswordRepository.save(new ResetPasswordMapper(email,code,  System.currentTimeMillis()+this.expiration));
        //sending the mail
try{
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("khmoussiaouina@gmail.com");
    msg.setTo(email);

    msg.setSubject("Issatc Reset Password");
    msg.setText(  "write the code bellow with a new password in your application :"  + ". \n\n" + Integer.toString(code) +"\n"
            + "Regards \n");

    javaMailSender.send(msg);

}catch (Exception e){
    e.printStackTrace();
}

    }

    @Override
    public boolean changePassword(NewPasswordRequest newPasswordRequest) {
boolean result =(this.resetPasswordRepository.verifyCode(newPasswordRequest.getEmail(),newPasswordRequest.getRecoveryCode(),System.currentTimeMillis()))>=1;
    if(result){
        this.authenticationRepository.updatePassword(newPasswordRequest.getEmail(),this.passwordEncoder.encode(newPasswordRequest.getPassword()));
    }
    return  result;
    }


    int generateRecoveryCode(){
        // Create a Random object
        Random random = new Random();

        // Generate a random 6-digit number
        int randomNumber = random.nextInt(1000000); // Generates a number between 0 and 999999

        // Print the random number with leading zeros if necessary
        String formattedNumber = String.format("%06d", randomNumber);
        System.out.println("Random 6-digit number: " + formattedNumber);
        return randomNumber;
    }

    //initializing the admin or admins
    @PostConstruct
    private void postConstruct() {
        authenticationRepository.save(new UserMapper("aouinakhmoussi716@gmail.com","student"+"Firstname","student"+"Lastname",Role.STUDENT));


        ArrayList<UserMapper> usersList= new ArrayList<UserMapper>();
        if(!authenticationRepository.existsById("admin@Gmail.com")){
            UserMapper admin=new UserMapper("admin@Gmail.com","admin","admin",passwordEncoder.encode("admin"), Role.ADMIN,75111111);
                authenticationRepository.save(admin);
        }

        // creating sectors
        for(int i=1;i<=3;i++){
            if(!sectorRepository.existsById("Licence A" + String.valueOf(i))){
                sectorRepository.save(new SectorMapper("Licence A" + String.valueOf(i)));
            }
        }
        for(int i=1;i<=3;i++){
            if(!sectorRepository.existsById("Ing A" + String.valueOf(i))){
                sectorRepository.save(new SectorMapper("Ing A" + String.valueOf(i)));
            }
        }
        for(int i=1;i<=2;i++){
            if(!sectorRepository.existsById("Prepa A" + String.valueOf(i))){
                sectorRepository.save(new SectorMapper("Prepa A" + String.valueOf(i)));
            }
        }


        //creating students
      for(int i =1 ;i<=30;i++){
          if(!authenticationRepository.existsById("student"+String.valueOf(i)+"@gmail.com"))

          {if(i<=5)
              authenticationStudentRepository.save(new StudentMapper("student"+String.valueOf(i)+"@gmail.com","student"+String.valueOf(i)+"Firstname","student"+String.valueOf(i)+"Lastname",Role.STUDENT,i+10000,this.sectorRepository.findById("Prepa A1").orElseThrow()));
              if(5<i&& i<=10)
                  authenticationStudentRepository.save(new StudentMapper("student"+String.valueOf(i)+"@gmail.com","student"+String.valueOf(i)+"Firstname","student"+String.valueOf(i)+"Lastname",Role.STUDENT,i+10000,this.sectorRepository.findById("Prepa A2").orElseThrow()));
              if(10<i&& i<=15)
                  authenticationStudentRepository.save(new StudentMapper("student"+String.valueOf(i)+"@gmail.com","student"+String.valueOf(i)+"Firstname","student"+String.valueOf(i)+"Lastname",Role.STUDENT,i+10000,this.sectorRepository.findById("Licence A1").orElseThrow()));
              if(15<i&& i<=20)
                  authenticationStudentRepository.save(new StudentMapper("student"+String.valueOf(i)+"@gmail.com","student"+String.valueOf(i)+"Firstname","student"+String.valueOf(i)+"Lastname",Role.STUDENT,i+10000,this.sectorRepository.findById("Licence A2").orElseThrow()));

              if(20<i&& i<=25)
                  authenticationStudentRepository.save(new StudentMapper("student"+String.valueOf(i)+"@gmail.com","student"+String.valueOf(i)+"Firstname","student"+String.valueOf(i)+"Lastname",Role.STUDENT,i+10000,this.sectorRepository.findById("Licence A3").orElseThrow()));

              if(25<i&& i<=30)
                  authenticationStudentRepository.save(new StudentMapper("student"+String.valueOf(i)+"@gmail.com","student"+String.valueOf(i)+"Firstname","student"+String.valueOf(i)+"Lastname",Role.STUDENT,i+10000,this.sectorRepository.findById("Ing A1").orElseThrow()));



          }

      }
        for(int i =1 ;i<=5;i++){
            if(!authenticationRepository.existsById("teacher"+String.valueOf(i)+"@gmail.com"))
                authenticationTeacherRepository.save(new TeacherMapper("teacher"+String.valueOf(i)+"@gmail.com","teacher"+String.valueOf(i)+"Firstname","teacher"+String.valueOf(i)+"Lastname",Role.TEACHER,i+13275684));

        }

        // creating departments
     //to fix
          for(int i=1;i<=5;i++){
            if(!departmentRepository.existsById(i)){
                departmentRepository.save(new DepartmentMapper("department" + String.valueOf(i)));
            }
        }


    }
}
