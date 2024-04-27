package com.example.issatc.Ports;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Requests.StudentPresenceRequest;
import com.example.issatc.Entities.Requests.SubjectAbsence;
import com.example.issatc.Entities.Responses.GroupSchedule;
import com.example.issatc.Entities.Responses.SubjectWithGroups;
import com.example.issatc.Entities.Responses.TeacherSchedule;
import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Infrastructure.EntityMappers.ClassRoom;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataServicePort {
    List<TeacherWithDepResponse> getTeachers();

    void assigningGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames,String sectorId) ;

    Map<String, Integer> saveGroups(List<GroupsBySectorRequest>List);

    List<Departement> getDepartments();

    List<Sector> getSectors();
    List<SubjectWithGroups>getTeacherSubjectGroups(String email);

    boolean saveSeances(String email, List<Seance> seanceList);

    List<TeacherSchedule> getTeacherSchedule(String email);

    boolean groupExists(int groupID);

    List<GroupSchedule> getGroupSchedule(int groupId);

    int getStudentGroup(String email);

    boolean markPresence(StudentPresenceRequest request);

    List<SubjectAbsence> getAbsence(String email);

    List<Student> getStudentBySector(String sectorId);

    boolean sectorExists(String sectorId);

    List<Classroom> getClassRooms();
}
