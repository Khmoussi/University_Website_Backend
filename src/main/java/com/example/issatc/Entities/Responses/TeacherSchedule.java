package com.example.issatc.Entities.Responses;

import com.example.issatc.Entities.Group;
import com.example.issatc.Entities.Sector;
import com.example.issatc.Infrastructure.EntityMappers.ClassRoom;
import lombok.Data;

@Data
public class TeacherSchedule {
    String classRoomName;
    String groupName;
    String sectorName;
    int seanceNum;
    String day;

    public TeacherSchedule(String classRoomName, String groupName, String sectorName, int seanceNum, String day) {
        this.classRoomName = classRoomName;
        this.groupName = groupName;
        this.sectorName = sectorName;
        this.seanceNum = seanceNum;
        this.day = day;
    }
}
