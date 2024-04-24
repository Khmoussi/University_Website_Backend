package com.example.issatc.Ports;

import com.example.issatc.Entities.Departement;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Responses.SubjectWithGroups;
import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Entities.Sector;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DataServicePort {
    List<TeacherWithDepResponse> getTeachers();

    void assigningGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames);

    Map<String, Integer> saveGroups(List<GroupsBySectorRequest>List);

    List<Departement> getDepartments();

    List<Sector> getSectors();
    List<SubjectWithGroups>getTeacherSubjectGroups(String email);
}
