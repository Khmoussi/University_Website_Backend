package com.example.issatc.Infrastructure;

import com.example.issatc.Entities.Sector;
import com.example.issatc.Infrastructure.EntityMappers.SectorMapper;
import com.example.issatc.Infrastructure.EntityMappers.SubjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaSectorRepository extends JpaRepository<SectorMapper,String> {
  @Query("select new com.example.issatc.Entities.Sector(s.name) from sector s ")
    List<Sector> getSectors();


  @Query("select s.sector from student s where s.email =:email")
  SectorMapper getSectorByStudentId(@Param("email") String email);

  @Modifying
  @Transactional
  @Query(value = "insert into sector_subject  (sector_id , subject_id) values(:name,:subject_id)" ,nativeQuery = true)
    int saveSectorSubject(@Param("name") String name, @Param("subject_id") int id);
}
