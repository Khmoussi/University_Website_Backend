package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaClassRoomRepository extends JpaRepository<ClassRoom,Integer> {
    @Query("select new com.example.issatc.Entities.Classroom(c.id ,c.name) from ClassRoom c")
    List<Classroom> getClassRoom();
}
