package com.example.issatc.Infrastructure;

import com.example.issatc.Infrastructure.EntityMappers.RecordMapper;
import jakarta.transaction.Transactional;
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
    void setPresence(@Param("email") String email, @Param("subjectId")int subjectId, @Param("teacherMail")String teacherMail);

  @Query ("select new com.example.issatc.Entities.Requests.SubjectAbsence(r.subject.name , r.absenceNum , r.subject.type ,r.subject.semester) from record r where r.student.email =:email ")
  List<SubjectAbsence> getRecordByStudent(@Param("email") String email);
}
