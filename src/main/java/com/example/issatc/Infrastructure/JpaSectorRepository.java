package com.example.issatc.Infrastructure;

import com.example.issatc.Entities.Sector;
import com.example.issatc.Infrastructure.EntityMappers.SectorMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaSectorRepository extends JpaRepository<SectorMapper,String> {
  @Query("select new com.example.issatc.Entities.Sector(s.name) from sector s ")
    List<Sector> getSectors();
}
