package com.example.issatc.Entities.Responses;

import lombok.Data;

@Data
public class GroupSchedule {
    String classRoomName;
    String teacherFirstName;
    String teacherLastName;
    int seanceNum;
    String day;

    public GroupSchedule(String classRoomName, String teacherFirstName, String teacherLastName, String sectorName, int seanceNum, String day) {
        this.classRoomName = classRoomName;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.seanceNum = seanceNum;
        this.day = day;
    }
}