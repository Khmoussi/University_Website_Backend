package com.example.issatc.Infrastructure;

import com.example.issatc.Infrastructure.EntityMappers.RecordMapper;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.issatc.Entities.Requests.SubjectAbsence;

import java.util.List;

public interface JpaRecordRepository extends JpaRepository<RecordMapper,RecordMapper.RecordId> {
  @Transactional
  @Modifying
   @Query("update  record r set r.absenceNum =r.absenceNum + 1 where r.teacher.email =:teacherMail and r.subject.id =:subjectId and student.email =:email  ")
    int setPresence(@Param("email") String email, @Param("subjectId")int subjectId, @Param("teacherMail")String teacherMail);

    @Transactional
    @Modifying
    @Query(value = "insert into record (absence_num,note_num,subject_id,teacher_id,student_id)  values(1,-1,:subjectId,:teacherMail,:email)  ",nativeQuery = true)
    void createPresence(@Param("email") String email, @Param("subjectId")int subjectId, @Param("teacherMail")String teacherMail);

    @Query ("select new com.example.issatc.Entities.Requests.SubjectAbsence(r.subject.name , r.absenceNum , r.subject.type ,r.subject.semester) from record r where r.student.email =:email ")
  List<SubjectAbsence> getRecordByStudent(@Param("email") String email);
    @Transactional(dontRollbackOn = DataIntegrityViolationException.class)
    @Modifying
    @Query(value = "insert into record  (subject_id,student_id , note_num ,absence_num,teacher_id) values(:subjectId,:email,:note,0,:teacherMail)",nativeQuery = true)
    int saveNote(@Param("subjectId") int subjectId, @Param("email") String email, @Param("teacherMail") String teacherMail, @Param("note") int note);

    @Transactional
    @Modifying
    @Query("update   record r  set r.noteNum =:note  where r.subject.id =:subjectId and r.student.email =:email  and r.teacher.email =:teacherMail ")

    int updateNote( @Param("subjectId") int subjectId, @Param("email") String email,@Param("teacherMail") String teacherMail, @Param("note") int note);

    @Query("select  r.noteNum from record r where r.student.email =:email and r.subject.id =:id")
    Integer getNoteByEmailSubject(@Param("email") String email,@Param("id") int id);

/*    @Query("select count(*) from record r where  r.subject.id =:subjectId and  r.student.email in (select s.email from student s where s.group.id =:groupId ) ")
    int subjectWithGroupRecordExists(@Param("groupId") int groupId, @Param("subjectId") int subjectId);

 */
}
