package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaSeanceRepository extends JpaRepository<SeanceMapper,SeanceMapper.SeanceId> {

@Transactional
@Modifying
@Query(value = "insert into seance (teacher_id,day,seance_numb,group_id,class_room_id,subject_id) values (:email, :day , :seanceNumb , :groupId, :classRoomId , :subjectId)",nativeQuery = true)
    int saveSeance(@Param("email") String email, @Param("day")String day, @Param("seanceNumb")int seanceNumb, @Param("groupId")int groupId, @Param("classRoomId")int classRoomId,@Param("subjectId") int subjectId);

@Query(value = "select s from  seance s where s.teacher.email =:email")
    List<SeanceMapper> getSeancesByTeacherId(@Param("email") String email);
    @Query(value = "select s from  seance s where s.group.id =:id")

    List<SeanceMapper> getSeancesByGroupId(@Param("id") int groupId);
}
