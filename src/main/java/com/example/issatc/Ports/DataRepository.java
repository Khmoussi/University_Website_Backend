package com.example.issatc.Ports;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.GroupGradesRequest;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Requests.StudentPresenceRequest;
import com.example.issatc.Entities.Requests.SubjectAbsence;
import com.example.issatc.Entities.Responses.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DataRepository {
    List<TeacherWithDepResponse> getAllTeachers();

    List<TeacherWithDepResponse> getChefs();

    void assignGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames,String sectorId) ;

    Map<String, Integer> saveGroups(List<GroupsBySectorRequest> groupName);

    List<Departement> getDepartments();

    List<Sector> getSectors();

    List<Subject> getTeacherSubjects(String email);
    List<Group>getGroupBySector(String sectorId);

    List<Sector> getSectorsBySubject();

    List<SubjectWithGroups> getTeacherSubjectsGroups(String email);

    int saveSeances(String email, List<Seance> seanceList);

    List<TeacherSchedule> getTeacherSchedule(String email);

    boolean groubExistsById(int groupId);

    List<GroupSchedule> getGroupSchedule(int groupId);

    int getStudentGroup(String email);

    boolean markPresence(StudentPresenceRequest request);

    List<SubjectAbsence> getAbsence(String email);

    List<Student> getStudentBySector(String sectorId);

    boolean sectorExists(String sectorId);

    List<Classroom> getClassRooms();

    List<Student> getStudentByGroup(int groupID);

    boolean postGroupGrades(GroupGradesRequest request);

    boolean groupExistsInSector(int groupId, String sectorId);

    boolean subjectInSector(GroupGradesRequest request);

    List<SubjectWithNote> getGrades(String email);

    boolean departmentExistsById(String departmentName);
}
