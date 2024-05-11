package com.example.issatc.Entities.Responses;

import com.example.issatc.Infrastructure.EntityMappers.SubjectType;
import lombok.Data;

@Data
public class TeacherSchedule {
    String classRoomName;
    String groupName;
    String sectorName;
    int seanceNum;
    String day;
    String subjectName;
    String subjectType;
    int groupId;
    int subjectId;

    public TeacherSchedule(String classRoomName, String groupName, String sectorName, int seanceNum, String day, String subjectName, SubjectType type,int groupId,int subjectId) {
        this.classRoomName = classRoomName;
        this.groupName = groupName;
        this.sectorName = sectorName;
        this.seanceNum = seanceNum;
        this.day = day;
        this.subjectName=subjectName;
        this.subjectType=type.name();
        this.groupId=groupId;
        this.subjectId=subjectId;
    }


}
