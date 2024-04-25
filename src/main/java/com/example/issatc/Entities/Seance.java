package com.example.issatc.Entities;

import lombok.Data;

@Data
public class Seance {
    private int groupId;
    //private TeacherMapper teacher;
    private int subjectId;
    private int classRoomId;

    private String day;
    private int seanceNumb;
}
