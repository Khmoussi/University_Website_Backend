package com.example.issatc.Ports;

import com.example.issatc.Entities.Departement;
import com.example.issatc.Entities.Group;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Responses.SubjectWithGroups;
import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Entities.Sector;
import com.example.issatc.Entities.Subject;

import java.util.List;
import java.util.Map;

public interface DataRepository {
    List<TeacherWithDepResponse> getAllTeachers();

    List<TeacherWithDepResponse> getChefs();

    void assignGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames);

    Map<String, Integer> saveGroups(List<GroupsBySectorRequest> groupName);

    List<Departement> getDepartments();

    List<Sector> getSectors();

    List<Subject> getTeacherSubjects(String email);
    List<Group>getGroupBySector(String sectorId);

    List<Sector> getSectorsBySubject();

    List<SubjectWithGroups> getTeacherSubjectsGroups(String email);
}
