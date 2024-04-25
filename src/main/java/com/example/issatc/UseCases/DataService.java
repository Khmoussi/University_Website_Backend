package com.example.issatc.UseCases;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Responses.GroupSchedule;
import com.example.issatc.Entities.Responses.SubjectWithGroups;
import com.example.issatc.Entities.Responses.TeacherSchedule;
import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Ports.DataRepository;
import com.example.issatc.Ports.DataServicePort;

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
    public void assigningGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames) {

        this.dataRepository.assignGroups(list,groupNames);

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


}
