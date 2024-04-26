package com.example.issatc.Entities.Requests;

import lombok.Data;

import java.util.List;

@Data
public class StudentPresenceRequest {

    String teacherId;
    int subjectId ;
    List<StudentPresence> students;
}
