package com.example.issatc.Entities.Responses;

import com.example.issatc.Entities.Subject;
import lombok.Data;

import java.util.List;

@Data
public class TeacherScheduleResponse {
    private List<SubjectWithGroups> subjectList;


}
