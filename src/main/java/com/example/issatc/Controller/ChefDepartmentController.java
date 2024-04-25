package com.example.issatc.Controller;

import com.example.issatc.Entities.Group;
import com.example.issatc.Entities.Requests.ResetRequest;
import com.example.issatc.Entities.ScheduleRequest;
import com.example.issatc.Infrastructure.EntityMappers.JpaTeacherRepository;
import com.example.issatc.Infrastructure.EntityMappers.Request.GroupScheduleRequest;
import com.example.issatc.Ports.AuthenticationServicePort;
import com.example.issatc.Ports.DataServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chef")
public class ChefDepartmentController {
    private final DataServicePort dataService;
    private final JpaTeacherRepository teacherRepository;
    private final AuthenticationServicePort authenticationService;


    @GetMapping("/gestionEmploie")
    ResponseEntity<?> getTeacherSubjectGroups(@RequestBody ResetRequest request){
        try {
            if (request.getEmail().equals("") || request == null || request.getEmail() == null)
                return ResponseEntity.badRequest().body("empty mail");

            return ResponseEntity.ok().body(this.dataService.getTeacherSubjectGroups(request.getEmail()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("empty mail");

        }
    }

    @PostMapping("/scheduleCreation")
    ResponseEntity<?> saveSchedule(@RequestBody ScheduleRequest request){

        try{
            this.dataService.saveSeances(request.getEmail(), request.getSeanceList());
            return ResponseEntity.ok().body("saved Successfully");

        }catch (DataIntegrityViolationException e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Key constraints are encountred");

        }catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.badRequest().body("Unknown error");

        }
    }

    @GetMapping("/getTeacherSchedule")
    ResponseEntity<?> getTeacherSchedule(@RequestBody ResetRequest request){


        if (request.getEmail().equals("") || request == null || request.getEmail() == null)
            return ResponseEntity.badRequest().body("empty mail");

        if (!this.teacherRepository.existsById(request.getEmail()))
            return ResponseEntity.badRequest().body("email does not exists");

        try{
return ResponseEntity.ok(this.dataService.getTeacherSchedule(request.getEmail()));

        }catch (Exception e){
            return  ResponseEntity.internalServerError().body("unknown error");
        }

    }

    @GetMapping("/getGroupSchedule")
    ResponseEntity<?> getGroupSchedule(@RequestBody ResetRequest request ){
        if (request.getEmail().equals("") || request == null || request.getEmail() == null)
            return ResponseEntity.badRequest().body("empty mail");

        if (!this.authenticationService.studentExistsById(request.getEmail()))
            return ResponseEntity.badRequest().body("email does not exists");

            try{
            int groupId=this.dataService.getStudentGroup(request.getEmail());
            return ResponseEntity.ok().body(this.dataService.getGroupSchedule(groupId));
        }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().body("student do not have group yet");

            }

    }


    @GetMapping("/hello")
    String hello(){
        return "hello chef";
    }

}
