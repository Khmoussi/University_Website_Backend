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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    ResponseEntity<?> getAbsence(@RequestBody ResetRequest request ,Principal principal){
        UserMapper user = (UserMapper) ((Authentication) principal).getPrincipal();
        try {
        if(!request.getEmail().equals(user.getEmail()))
            throw new UnauthorizedException(request.getEmail());
        List<SubjectAbsence> list;


            list=this.dataService.getAbsence(request.getEmail());
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
    ResponseEntity<?> getGroupSchedule(@RequestBody ResetRequest request ,Principal principal){
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
    ResponseEntity<?> getGrades(@RequestBody ResetRequest request, Principal principal){
       try {
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
