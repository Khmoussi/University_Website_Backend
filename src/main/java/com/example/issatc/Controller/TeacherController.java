package com.example.issatc.Controller;

import com.example.issatc.Entities.Requests.StudentPresenceRequest;
import com.example.issatc.Ports.DataServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

    private final DataServicePort dataService;
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


}
