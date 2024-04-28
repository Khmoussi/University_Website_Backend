package com.example.issatc.Controller;

import com.example.issatc.Entities.Group;
import com.example.issatc.Entities.Requests.ResetRequest;
import com.example.issatc.Entities.Responses.GroupSchedule;
import com.example.issatc.Entities.ScheduleRequest;
import com.example.issatc.Infrastructure.EntityMappers.JpaTeacherRepository;
import com.example.issatc.Infrastructure.EntityMappers.Request.GroupScheduleRequest;
import com.example.issatc.Ports.AuthenticationServicePort;
import com.example.issatc.Ports.DataServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chef")
public class ChefDepartmentController {
    private final DataServicePort dataService;
    private final JpaTeacherRepository teacherRepository;
    private final AuthenticationServicePort authenticationService;


    @GetMapping("/gestionEmploie")
    ResponseEntity<?> getTeacherSubjectGroups(@RequestParam String email){
        try {
            ResetRequest request=new ResetRequest(email);
            if (request.getEmail().equals("") || request == null || request.getEmail() == null)
                return ResponseEntity.badRequest().body("empty mail");

            return ResponseEntity.ok().body(this.dataService.getTeacherSubjectGroups(request.getEmail()));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Wrong Email");

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
    ResponseEntity<?> getTeacherSchedule(@RequestParam String email){

        ResetRequest request=new ResetRequest(email);

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

   /* @GetMapping("/getGroupSchedule")
    ResponseEntity<?> getGroupSchedule(@RequestParam String email ){
        ResetRequest request=new ResetRequest(email);

        if (request.getEmail().equals("") || request == null || request.getEmail() == null)
            return ResponseEntity.badRequest().body("empty mail");

        if (!this.authenticationService.studentExistsById(request.getEmail()))
            return ResponseEntity.badRequest().body("email does not exists");

            try{
            int groupId=this.dataService.getStudentGroup(request.getEmail());
            List<GroupSchedule> list  =this.dataService.getGroupSchedule(groupId);
            System.out.println(list.get(0).getDay() +"wwwwwwww");
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().body("student do not have group yet");

            }

    }

    */
    @GetMapping("/getClassRooms")
    ResponseEntity<?>getClassRooms(){
        return ResponseEntity.ok().body( this.dataService.getClassRooms());
    }


    @GetMapping("/hello")
    String hello(){
        return "hello chef";
    }

}
