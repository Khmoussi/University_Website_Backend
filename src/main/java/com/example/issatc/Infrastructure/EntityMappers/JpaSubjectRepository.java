package com.example.issatc.Infrastructure.EntityMappers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaSubjectRepository extends JpaRepository<SubjectMapper,Integer> {
    @Query("select s from SubjectMapper s where s.name =:name ")
    List<SubjectMapper> findAllByName(@Param("name") String name);
}
