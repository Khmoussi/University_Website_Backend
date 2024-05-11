package com.example.issatc.Entities.Responses;

import com.example.issatc.Infrastructure.EntityMappers.SubjectType;
import lombok.Data;

@Data
public class GroupSchedule {
    String classRoomName;
    String teacherFirstName;
    String teacherLastName;
    int seanceNum;
    String day;
     String subjectName;
    String type;
    int groupId;
    int subjectId;

    public GroupSchedule(String classRoomName, String teacherFirstName, String teacherLastName, String sectorName, int seanceNum, String day, String name1, SubjectType type,int groupId,int subjectId)
    {
        this.classRoomName = classRoomName;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.seanceNum = seanceNum;
        this.day = day;
        this.subjectName=name1;
        this.type=type.name();
        this.groupId=groupId;
        this.subjectId=subjectId;
    }


}