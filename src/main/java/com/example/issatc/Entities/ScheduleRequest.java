package com.example.issatc.Entities;

import com.example.issatc.Infrastructure.EntityMappers.ClassRoom;
import com.example.issatc.Infrastructure.EntityMappers.GroupMapper;
import com.example.issatc.Infrastructure.EntityMappers.SubjectMapper;
import com.example.issatc.Infrastructure.EntityMappers.TeacherMapper;
import lombok.Data;

import java.util.List;

@Data
public class ScheduleRequest {

    private String email;
    private List<Seance> seanceList;

    public ScheduleRequest(String email, List<Seance> seanceList) {
        this.email = email;
        this.seanceList = seanceList;
    }
}
