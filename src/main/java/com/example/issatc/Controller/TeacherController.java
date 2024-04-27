package com.example.issatc.Controller;

import com.example.issatc.Entities.Requests.ResetRequest;
import com.example.issatc.Entities.Requests.StudentPresenceRequest;
import com.example.issatc.Entities.Student;
import com.example.issatc.Infrastructure.EntityMappers.JpaTeacherRepository;
import com.example.issatc.Infrastructure.EntityMappers.Request.GroupScheduleRequest;
import com.example.issatc.Ports.DataServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final DataServicePort dataService;
    private final JpaTeacherRepository teacherRepository;

    @GetMapping("/hello")
    String hello(){
        return "hello teacher";
    }

    @PutMapping("/StudentPresence")
    ResponseEntity<?> markStudentsPresenceByGroup(@RequestBody StudentPresenceRequest request ){

try {
    boolean result =this.dataService.markPresence(request);
    if(result)
        return ResponseEntity.ok().body("updates are made successfully");
}catch (Exception e){
    e.printStackTrace();
}

        return ResponseEntity.badRequest().body("can't update");

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

    @GetMapping("/getStudentByGroup")
        ResponseEntity<?> getStudentByGroup(@RequestBody GroupScheduleRequest request){
        try {

            if (request == null || !this.dataService.groupExists(request.getGroupID()))
                return ResponseEntity.badRequest().body("group does not exists");

            List<Student> list = this.dataService.getStudentByGroup(request);

            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            return  ResponseEntity.internalServerError().body("unknown error");
        }
    }




}
