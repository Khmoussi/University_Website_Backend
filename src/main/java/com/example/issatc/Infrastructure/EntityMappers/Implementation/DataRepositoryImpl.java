package com.example.issatc.Infrastructure.EntityMappers.Implementation;

import com.example.issatc.Entities.Departement;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Entities.Sector;
import com.example.issatc.Infrastructure.EntityMappers.*;
import com.example.issatc.Infrastructure.JpaSectorRepository;
import com.example.issatc.Ports.DataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component

@RequiredArgsConstructor
public class DataRepositoryImpl implements DataRepository {
    private final JpaTeacherRepository teacherRepository;
    private final JpaGroupRepository groupRepository;
    private final JpaStudentRepository studentRepository;
    private final JpaDepartmentRepository departmentRepository;
    private final JpaSectorRepository sectorRepository;
    @Override
    public List<TeacherWithDepResponse> getAllTeachers() {
        return this.teacherRepository.getAllTeachers();
    }

    @Override
    public List<TeacherWithDepResponse> getChefs() {
        return this.teacherRepository.getChefs();
    }

    @Override
    @Transactional
    public void assignGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames) {
        int i=0;

       // System.out.println("listsize^p " +list.size());
        while(i< list.size()){
            int j=0;
            while (j<list.get(i).getListStudents().size()) {

                this.studentRepository.updateStudentGroup(list.get(i).getListStudents().get(j), groupNames.get(list.get(i).getGroupName()));
            j++;
          // System.out.println("grou^p " +groupNames.get(list.get(1).getGroupName()));
            }
      i++;
        }
    }

    @Transactional
    @Override
    public Map<String, Integer> saveGroups(List<GroupsBySectorRequest> groupNames) {
        int i=0;
 Map<String,Integer> map=new HashMap<>();
        while(i< groupNames.size()){

          GroupMapper group=  this.groupRepository.save(new GroupMapper(groupNames.get(i).getGroupName()));
            map.put(groupNames.get(i).getGroupName(), group.getId());
i++;
        }
        return map;
    }

    @Override
    public List<Departement> getDepartments() {
        return this.departmentRepository.getDepartments();
    }

    @Override
    public List<Sector> getSectors() {
        return this.sectorRepository.getSectors();
    }
}
