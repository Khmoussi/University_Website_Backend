package com.example.issatc.Controller;

import com.example.issatc.Entities.Requests.*;
import com.example.issatc.Infrastructure.EntityMappers.Request.AssignChefRequest;
import com.example.issatc.Infrastructure.EntityMappers.Request.AssigningGroupsBySectorRequest;
import com.example.issatc.Infrastructure.EntityMappers.Request.TeacherWithDepRequest;
import com.example.issatc.Ports.AuthenticationServicePort;
import com.example.issatc.Ports.DataServicePort;
import com.example.issatc.utilities.JwtAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AuthenticationServicePort authenticationService;
    private final DataServicePort dataService;
    private final JwtAuthenticationService jwtAuthenticationService;
    @GetMapping("/hello")
    String hello(){
        return "hello admin";
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNullPointerException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("a group does not exists");
    }
    @ExceptionHandler(UnexpectedRollbackException.class)
    public ResponseEntity<String> handleUnexpectedRollbackException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("An inscription number doesn't belong to the sector");
    }
    @PostMapping("/SignStudentUser")
    ResponseEntity<?> createAccount(@RequestBody StudentAccountRequest accountRequest){
        //true user exists doesn't have account
        //false
        int result=authenticationService.createStudentAccount(accountRequest);
        switch (result){
            case 1:         return ResponseEntity.ok().body("Account Created");

            case 2: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknow error");

        }

    }

    @DeleteMapping("/DeleteStudentAccount")
    ResponseEntity<?> deleteAccount(@RequestBody DeleteAccountRequest deleteAccountRequest){
        int result=authenticationService.deleteAccount( deleteAccountRequest.getEmail());
        switch (result){
            case 1:         return ResponseEntity.ok().body("Account deleted");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknown error");

        }

    }

    @PutMapping("/ModifyStudentAccount")
    ResponseEntity<?> modifyAccount(@RequestBody StudentAccountRequest accountRequest){

        int result=authenticationService.modifyStudentAccount(accountRequest);
        switch (result){
            case 1:         return ResponseEntity.badRequest().body("Account not found");

            case 2: return ResponseEntity.status(HttpStatus.OK).body("Account modified");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknow error");

        }
    }


    //Gérer les enseignants
    @PostMapping("/SignTeacherUser")
    ResponseEntity<?> createTeacherAccount(@RequestBody TeacherAccountRequest accountRequest){
        //true user exists doesn't have account
        //false
        int result=authenticationService.createTeacherAccount(accountRequest);
        switch (result){
            case 1:         return ResponseEntity.ok().body("Account Created");

            case 2: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("bad credentials");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknow error");

        }

    }

    @DeleteMapping("/DeleteTeacherAccount")
    ResponseEntity<?> deleteTeacherAccount(@RequestBody String email){
        int result=authenticationService.deleteAccount( email);
        switch (result){
            case 1:         return ResponseEntity.ok().body("Account Created");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknow error");

        }

    }

    @PutMapping("/ModifyTeacherAccount")
    ResponseEntity<?> modifyTeacherAccount(@RequestBody TeacherAccountRequest accountRequest){

        int result=authenticationService.modifyTeacherAccount(accountRequest);
        switch (result){
            case 1:         return ResponseEntity.badRequest().body("Account not found");

            case 2: return ResponseEntity.status(HttpStatus.OK).body("Account modified");

            case 0: return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
            default:return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unknown error");

        }
    }

    @Transactional
    @PutMapping("/modifyTeacherListing")

    ResponseEntity<?> modifyTeacherListing(@RequestBody TeacherWithDepRequest request){
try {
    TeacherWithDepRequest r = request;
    if (this.authenticationService.modifyTeacherAccountNoPsd(request.getFirstName(), request.getLastName(), request.getEmail(), request.getCin(), request.getPhoneNum())
            &&
            this.authenticationService.modifyTeacherDep(request.getEmail(), request.getDepartmentName()))

        return ResponseEntity.ok().body("modification succeded");
    return ResponseEntity.badRequest().body("modification failed");
}catch (Exception e){
    e.printStackTrace();
    return ResponseEntity.badRequest().body("modification failed");

}

    }

    @PostMapping("/assignChef")
    ResponseEntity<?> assignChef(@RequestBody DepartmentChefRequest request){
try {
    int result = this.authenticationService.saveDepartmentChef(request);
    if (result > 0)
        return ResponseEntity.ok().body("success assignment");
    return ResponseEntity.badRequest().body("failed");
}catch (Exception e){
    return ResponseEntity.badRequest().body("a chef already exists in this department");

}
    }
    @GetMapping("/teachersList")
    ResponseEntity<?> getTeachers(){
return ResponseEntity.ok().body(this.dataService.getTeachers());
    }

    @Transactional
    @PostMapping("/assigningGroupsBySector")

    ResponseEntity<?> assigningGroupsBySector(@RequestBody AssigningGroupsBySectorRequest request){


        try {
            Map<String,Integer> map=this.dataService.saveGroups(request.getList());
            this.dataService.assigningGroups(request.getList(),map,request.getSectorId());
            return ResponseEntity.ok().body("saved success");

        }catch (NullPointerException e){
            System.out.println("wiwwwwwwwwwwwwwwww");
         e.printStackTrace();
         return ResponseEntity.badRequest().body("could not save");
        }

    }

    @GetMapping("/getDepartments")

ResponseEntity<?> getDepartments(){

        try{
           return ResponseEntity.ok().body( this.dataService.getDepartments());
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body("can't load");
        }
    }

    @GetMapping("/getSectors")
    ResponseEntity<?> getSectors() {
        try {
            return ResponseEntity.ok().body(this.dataService.getSectors());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("can't load");
        }
    }

      @GetMapping("/studentListBySector")
              ResponseEntity<?> getstudentListBySector(@RequestBody StudentListBySector request){
try {
    if(this.dataService.sectorExists(request.getSectorId()))
    return ResponseEntity.ok().body(this.dataService.getStudentBySector(request.getSectorId()));
    return ResponseEntity.badRequest().body("sectorId does not exist");

}catch (Exception e){
    return ResponseEntity.internalServerError().body("internalServerError");
}
        }











}
