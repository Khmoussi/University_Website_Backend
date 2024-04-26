package com.example.issatc.Infrastructure.EntityMappers.Implementation;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import com.example.issatc.Entities.Requests.StudentPresence;
import com.example.issatc.Entities.Requests.StudentPresenceRequest;
import com.example.issatc.Entities.Requests.SubjectAbsence;
import com.example.issatc.Entities.Responses.GroupSchedule;
import com.example.issatc.Entities.Responses.SubjectWithGroups;
import com.example.issatc.Entities.Responses.TeacherSchedule;
import com.example.issatc.Entities.Responses.TeacherWithDepResponse;
import com.example.issatc.Infrastructure.EntityMappers.*;
import com.example.issatc.Infrastructure.JpaRecordRepository;
import com.example.issatc.Infrastructure.JpaSectorRepository;
import com.example.issatc.Ports.DataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    private final JpaSeanceRepository seanceRepository;
    private final JpaRecordRepository recordRepository;

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
    public void assignGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames,String sectorId) {
        int i=0;

       // System.out.println("listsize^p " +list.size());
        while(i< list.size()){
            int j=0;
            while (j<list.get(i).getListStudents().size()) {
this.studentRepository.existsInSector(sectorId);
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

    @Override
    public List<Subject> getTeacherSubjects(String email) {
        TeacherMapper teacher=this.teacherRepository.findById(email).orElseThrow();
        List<SubjectMapper> list=teacher.getSubjects();
        List<Subject> resultList=new ArrayList<>();
        for (int i=0;i< list.size();i++){
            resultList.add(new Subject(list.get(i).getId(),list.get(i).getName(),list.get(i).getType(),list.get(i).getSemester(),list.get(i).getCoeff(),list.get(i).getSessionNumb()));
        }
        return resultList;
    }
    public List<SubjectMapper>  getTeacherSubjects2(String email) {
        TeacherMapper teacher=this.teacherRepository.findById(email).orElseThrow();
        List<SubjectMapper> list=teacher.getSubjects();

        return list;
    }

    @Override
    public List<Group> getGroupBySector(String sectorId) {
        List<Group> list= this.groupRepository.getGroupsBySector(sectorId);
        return list;
    }

    @Override
    public List<Sector> getSectorsBySubject() {
        return null;
    }

    @Override
    public List<SubjectWithGroups> getTeacherSubjectsGroups(String email) {
//getTeacherSubjects
        List<Group> groupList ;
        List<SubjectMapper> teacherSubjects =getTeacherSubjects2(email);
        List<SubjectWithGroups> subjectWithGroupsList =new ArrayList<>();
        for (SubjectMapper i:teacherSubjects
             ) {
            groupList =new ArrayList<>();
            for (SectorMapper j:i.getSectors()
                 ) {
                groupList.addAll(getGroupsBySector(j));
            }

subjectWithGroupsList.add(   new SubjectWithGroups(new Subject(i.getId(),i.getName(),i.getType(),i.getSemester(),i.getCoeff(),i.getSessionNumb()),groupList));
        }
        return subjectWithGroupsList;



    }

   @Transactional
    @Override
    public int saveSeances(String email, List<Seance> seanceList) {
        int i=0;
        for (Seance s:seanceList
             ) {
            this.seanceRepository.saveSeance(email,s.getDay(),s.getSeanceNumb(),s.getGroupId(),s.getClassRoomId(),s.getSubjectId());
        i++;
        }
        return i;
    }



    @Override
    public boolean groubExistsById(int groupId) {
        return this.groupRepository.existsById(groupId);
    }

    @Override
    public List<GroupSchedule> getGroupSchedule(int groupId) {
        List<SeanceMapper> seanceMappers =this.seanceRepository.getSeancesByGroupId(groupId);
        List<GroupSchedule> list=new ArrayList<>();
        for (SeanceMapper seance:seanceMappers
             ) {

            list.add(new GroupSchedule(seance.getClassRoom().getName(),seance.getTeacher().getFirstName(),seance.getTeacher().getLastName(),seance.getGroup().getGroupName(),seance.getSeanceNumb(),seance.getDay()));
        }
        return list;
    }

    @Override
    public int getStudentGroup(String email) {
        return this.getStudentGroup(email);
    }

    @Transactional
    @Override
    public boolean markPresence(StudentPresenceRequest request) {

        int subjectId= request.getSubjectId();
        String teacherMail=request.getTeacherId();

        List<StudentPresence> list= request.getStudents();

        for (StudentPresence s:list
             ) {
            if(!s.isPresence())
                try {
                    this.recordRepository.setPresence(s.getEmail(), subjectId, teacherMail);
                }catch (Exception e){
                    return  false;
                }
        }
        return true;
    }

    @Override
    public List<SubjectAbsence> getAbsence(String email) {
        List<SubjectAbsence> list=this.recordRepository.getRecordByStudent(email);
        List<SubjectMapper>  s= getSubjectsByStudent( email);
        for (SubjectAbsence subjectAbsence:list
             ) {
            for (SubjectMapper su:s
                 ) {
                if(!su.getName().equals(subjectAbsence.getSubjectName()))
                    list.add(new SubjectAbsence(su.getName(),0,su.getType(),su.getSemester()));

            }
        }
        return list;
    }
    List<SubjectMapper> getSubjectsByStudent(String email){
        SectorMapper s=this.sectorRepository.getSectorByStudentId(email);
       return  s.getSubjects();
    }

    @Override
    public List<TeacherSchedule> getTeacherSchedule(String email) {

        List<SeanceMapper> seanceMappers =this.seanceRepository.getSeancesByTeacherId(email);
        List<TeacherSchedule> teacherScheduleList =new ArrayList<>();
        for (SeanceMapper seance:seanceMappers
        ) {
            String sectorName=this.groupRepository.getSectorNameById(seance.getGroup().getId());
            teacherScheduleList.add(new TeacherSchedule(seance.getClassRoom().getName(),seance.getGroup().getGroupName(),sectorName,seance.getSeanceNumb(),seance.getDay()));
        }
        return teacherScheduleList;
    }

    List<Group> getGroupsBySector(SectorMapper sector){
return this.groupRepository.getGroupsBySector(sector.getName());
    }
    List<SectorMapper>SectorsBySubject(SubjectMapper subjectMapper){
       return subjectMapper.getSectors();
    }
}
