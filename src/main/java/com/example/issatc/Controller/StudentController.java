package com.example.issatc.Controller;

import com.example.issatc.Entities.Requests.ResetRequest;
import com.example.issatc.Entities.Requests.SubjectAbsence;
import com.example.issatc.Ports.DataServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")

public class StudentController {
    private final DataServicePort dataService;

    @GetMapping("/hello")
    String hello(){
        return "hello student";
    }

    @GetMapping("/getAbsence")
    ResponseEntity<?> getAbsence(@RequestBody ResetRequest request){
        List<SubjectAbsence> list;

        try {
            list=this.dataService.getAbsence(request.getEmail());
            return ResponseEntity.ok().body(list);
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseEntity.badRequest().body("unknown error");

        }
    }
}
