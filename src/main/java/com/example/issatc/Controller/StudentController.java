package com.example.issatc.Controller;

import com.example.issatc.Entities.Requests.ResetRequest;
import com.example.issatc.Entities.Requests.SubjectAbsence;
import com.example.issatc.Entities.Responses.GroupSchedule;
import com.example.issatc.Entities.Responses.SubjectWithNote;
import com.example.issatc.Infrastructure.EntityMappers.UserMapper;
import com.example.issatc.Infrastructure.UnauthorizedException;
import com.example.issatc.Ports.AuthenticationServicePort;
import com.example.issatc.Ports.DataServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")

public class StudentController {
    private final DataServicePort dataService;
    private final AuthenticationServicePort authenticationService;


    @GetMapping("/hello")
    String hello(){
        return "hello student";
    }

    @GetMapping("/getAbsence")
    ResponseEntity<?> getAbsence(@RequestParam String email , Principal principal){
        UserMapper user = (UserMapper) ((Authentication) principal).getPrincipal();
        try {
        if(!email.equals(user.getEmail()))
            throw new UnauthorizedException(email);
        List<SubjectAbsence> list;


            list=this.dataService.getAbsence(email);
            return ResponseEntity.ok().body(list);
        }catch (UnauthorizedException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Unauthorized User");
        }
        catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.internalServerError().body("unknown error");

        }
    }
    @GetMapping("/getGroupSchedule")
    ResponseEntity<?> getGroupSchedule(@RequestParam String email ,Principal principal){
        ResetRequest request= new ResetRequest(email);
        UserMapper user = (UserMapper) ((Authentication) principal).getPrincipal();
        try{
        if(!request.getEmail().equals(user.getEmail()))
            throw new UnauthorizedException(request.getEmail());
        if (request.getEmail().equals("") || request == null || request.getEmail() == null)
            return ResponseEntity.badRequest().body("empty mail");

        if (!this.authenticationService.studentExistsById(request.getEmail()))
            return ResponseEntity.badRequest().body("email does not exists");


            int groupId=this.dataService.getStudentGroup(request.getEmail());
            List<GroupSchedule> list  =this.dataService.getGroupSchedule(groupId);
            System.out.println(list.get(0).getDay() +"wwwwwwww");
            return ResponseEntity.ok().body(list);
        }catch (UnauthorizedException e){
            e.printStackTrace();
            return  ResponseEntity.internalServerError().body("Unauthorized User");

        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("student do not have group yet");

        }

    }

    @GetMapping("/getGrades")
    ResponseEntity<?> getGrades(@RequestParam String email, Principal principal){
       try {
           ResetRequest request= new ResetRequest(email);
           UserMapper user = (UserMapper) ((Authentication) principal).getPrincipal();
           if(!request.getEmail().equals(user.getEmail()))
               throw new UnauthorizedException(request.getEmail());

           if (!this.authenticationService.studentExistsById(request.getEmail()))
               return ResponseEntity.badRequest().body("email does not exists");

           List<SubjectWithNote> list = this.dataService.getGrades(request.getEmail());
           return ResponseEntity.ok().body(list);
       }catch (UnauthorizedException e){
           e.printStackTrace();
           return  ResponseEntity.internalServerError().body("Unauthorized User");

       }
       catch (Exception e){
           e.printStackTrace();
           return  ResponseEntity.internalServerError().body("unknown error");

       }

    }



}
