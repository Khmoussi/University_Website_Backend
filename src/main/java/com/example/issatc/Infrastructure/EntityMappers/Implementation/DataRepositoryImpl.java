package com.example.issatc.Infrastructure.EntityMappers.Implementation;

import com.example.issatc.Entities.*;
import com.example.issatc.Entities.Requests.*;
import com.example.issatc.Entities.Responses.*;
import com.example.issatc.Infrastructure.EntityMappers.*;
import com.example.issatc.Infrastructure.JpaRecordRepository;
import com.example.issatc.Infrastructure.JpaSectorRepository;
import com.example.issatc.Ports.DataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.SQLException;
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
    private final JpaClassRoomRepository classRoomRepository;
    private final JpaSubjectRepository subjectRepository;

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
    public void assignGroups(List<GroupsBySectorRequest> list,Map<String, Integer> groupNames,String sectorId)  {
        int i=0;

       // System.out.println("listsize^p " +list.size());
        while(i< list.size()){
            int j=0;
            while (j<list.get(i).getListStudents().size()) {
if(this.studentRepository.existsInSector(sectorId,list.get(i).getListStudents().get(j))>0){
    this.studentRepository.updateStudentGroup(list.get(i).getListStudents().get(j), groupNames.get(list.get(i).getGroupName()));

}else {
    try {
        throw new SQLException("Throwing exception for demoing rollback");
    }catch (Exception e){
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

    }

}
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

            list.add(new GroupSchedule(seance.getClassRoom().getName(),seance.getTeacher().getFirstName(),seance.getTeacher().getLastName(),seance.getGroup().getGroupName(),seance.getSeanceNumb(),seance.getDay(),seance.getSubject().getName(),seance.getSubject().getType(),seance.getGroup().getId(),seance.getSubject().getId()));
        }
        return list;
    }

    @Override
    public int getStudentGroup(String email) {
        return this.studentRepository.getStudentGroup(email);
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

                     if(   this.recordRepository.setPresence(s.getEmail(), subjectId, teacherMail)==0)
                         this.recordRepository.createPresence(s.getEmail(), subjectId, teacherMail);

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

    @Override
    public List<Student> getStudentBySector(String sectorId) {
        return  this.studentRepository.findBySectorId(sectorId);
    }

    @Override
    public boolean sectorExists(String sectorId) {
        return this.sectorRepository.existsById(sectorId);
    }

    @Override
    public List<Classroom> getClassRooms() {
        return this.classRoomRepository.getClassRoom();
    }

    @Override
    public List<Student> getStudentByGroup(int groupID) {
        return this.studentRepository.getStudentByGroup(groupID);
    }

    //doesn't work @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    @Override
    public boolean postGroupGrades(GroupGradesRequest request) {
        /*if(this.recordRepository.subjectWithGroupRecordExists(request.getGroupId(),request.getSubjectId())>0)
            return this.updateGroupGrades(request);

         */
int k=0;
        for (NumInscWithNote i:request.getList()
             ) {
            try {
                this.recordRepository.saveNote(request.getSubjectId(), i.getEmail(), request.getTeacherMail(), i.getNote());
           //throw new IllegalArgumentException(); mayrollbakich ki n7otha
            }catch (DataIntegrityViolationException e){
                e.printStackTrace();
               return( this.recordRepository.updateNote(request.getSubjectId(),i.getEmail(),request.getTeacherMail(),i.getNote())>0);

            }

            k++;
        }
        return (k==request.getList().size());

    }
   /* @Transactional
    public boolean updateGroupGrades(GroupGradesRequest request) {
        int k=0;
        for (NumInscWithNote i:request.getList()
        ) {

            this.recordRepository.updateNote(request.getSubjectId(),i.getEmail(),i.getNote());
            k++;
        }
        return (k==request.getList().size());

    }

    */

    @Override
    public boolean groupExistsInSector(int groupId, String sectorId) {
        return this.groupRepository.groupExistsInSector(groupId,sectorId)>=1;
    }

    @Override
    public boolean subjectInSector(GroupGradesRequest request) {
       int result=this.sectorRepository.subjectInSector(request.getSubjectId(),request.getSectorId());
       System.out.println("kl");
        return (result>0);
    }

    @Override
    public List<SubjectWithNote> getGrades(String email) {
        int intValue;
        List<SubjectWithNote> resultList=new ArrayList<>();
        List<SubjectMapper> list=   this.getSubjectsByStudent(email);
        for (SubjectMapper s:list
             ) {
            Integer note =this.recordRepository.getNoteByEmailSubject(email,s.getId());

                if(note!=null)
                   if(note==-1)
                       note=null;
                resultList.add(new SubjectWithNote(new Subject(s.getId(), s.getName(), s.getType(), s.getSemester(), s.getCoeff(), s.getSessionNumb()), note));


        }
         return resultList;
    }

    @Override
    public boolean departmentExistsById(String departmentName) {
        return this.departmentRepository.existsByName(departmentName)>=1;
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
            teacherScheduleList.add(new TeacherSchedule(seance.getClassRoom().getName(),seance.getGroup().getGroupName(),sectorName,seance.getSeanceNumb(),seance.getDay(),seance.getSubject().getName(),seance.getSubject().getType(),seance.getGroup().getId(),seance.getSubject().getId()));
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
