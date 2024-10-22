package com.example.issatc.UseCases;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.GroupGradesRequest;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Requests.StudentPresenceRequest;
import com.example.issatc.Entities.Requests.SubjectAbsence;
import com.example.issatc.Entities.Responses.*;
import com.example.issatc.Infrastructure.EntityMappers.Request.GroupScheduleRequest;
import com.example.issatc.Ports.DataRepository;
import com.example.issatc.Ports.DataServicePort;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DataService implements DataServicePort {
    private final DataRepository dataRepository;
    public DataService(DataRepository dataRepository){
        this.dataRepository=dataRepository;
    }


    @Override
    public List<TeacherWithDepResponse> getTeachers(){
        List<TeacherWithDepResponse> list1 =this.dataRepository.getAllTeachers();
        List<TeacherWithDepResponse> list2 =this.dataRepository.getChefs();
        for(int i=0;i<list2.size();i++){

            for(int j=0;j<list1.size();j++){
               if(list1.get(j).getEmail().equals(list2.get(i).getEmail())){
               list1.remove(j);
               list1.add(list2.get(i));
               }
            }
        }
        return list1;

    }

    @Override
    public void assigningGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames,String sectorId) {

        this.dataRepository.assignGroups(list,groupNames,sectorId);

    }

    @Override
    public Map<String, Integer> saveGroups(List<GroupsBySectorRequest> groupName) {
       return  this.dataRepository.saveGroups(groupName);
    }

    @Override
    public List<Departement> getDepartments() {
       return  this.dataRepository.getDepartments();
    }

    @Override
    public List<Sector> getSectors() {
        return  this.dataRepository.getSectors();

    }

    @Override
    public List<SubjectWithGroups> getTeacherSubjectGroups(String email) {
      return   this.dataRepository.getTeacherSubjectsGroups(email);

    }

    @Override
    public boolean saveSeances(String email, List<Seance> seanceList) {
       if(        this.dataRepository.saveSeances(email,seanceList)==seanceList.size())
        return true;
       return false;
    }

    @Override
    public List<TeacherSchedule> getTeacherSchedule(String email) {
       return this.dataRepository.getTeacherSchedule(email);
    }

    @Override
    public boolean groupExists(int groupId) {
        return this.dataRepository.groubExistsById(groupId);
    }

    @Override
    public List<GroupSchedule> getGroupSchedule(int groupId) {
        return this.dataRepository.getGroupSchedule(groupId);
    }

    @Override
    public int getStudentGroup(String email) {
        return this.dataRepository.getStudentGroup(email);
    }

    @Override
    public boolean markPresence(StudentPresenceRequest request) {
       return this.dataRepository.markPresence(request);
    }

    @Override
    public List<SubjectAbsence> getAbsence(String email) {
        return this.dataRepository.getAbsence(email);
    }

    @Override
    public List<Student> getStudentBySector(String sectorId) {
        return this.dataRepository.getStudentBySector(sectorId);
    }

    @Override
    public boolean sectorExists(String sectorId) {
        return this.dataRepository.sectorExists(sectorId);
    }

    @Override
    public List<Classroom> getClassRooms() {
        return this.dataRepository.getClassRooms();
    }

    @Override
    public List<Student> getStudentByGroup(GroupScheduleRequest request) {
        return this.dataRepository.getStudentByGroup(request.getGroupID());
    }

    @Override
    public boolean postGroupGrades(GroupGradesRequest request) {
        return this.dataRepository.postGroupGrades(request);
    }

    @Override
    public boolean groupExistsInSector(GroupGradesRequest request) {
       return this.dataRepository.groupExistsInSector(request.getGroupId(),request.getSectorId());
    }

    @Override
    public boolean groupSubjectInsector(GroupGradesRequest request) {
        return this.dataRepository.subjectInSector(request);
    }

    @Override
    public List<SubjectWithNote> getGrades(String email) {
        return this.dataRepository.getGrades(email);
    }

    @Override
    public boolean teacherExistsInGroupSubject(GroupGradesRequest request) {

       List<SubjectWithGroups> list = this.getTeacherSubjectGroups(request.getTeacherMail());
        for (SubjectWithGroups s:list
             ) {
            if(request.getSubjectId()==s.getSubject().getId()){
                for (Group g:s.getGroupList()
                     ) {
                    if(request.getGroupId()==g.getId())
                        return true;

                }
            }
        }
        return false;
    }

    @Override
    public boolean departmentExistsById(String departmentName) {
        return this.dataRepository.departmentExistsById(departmentName);
    }


}
