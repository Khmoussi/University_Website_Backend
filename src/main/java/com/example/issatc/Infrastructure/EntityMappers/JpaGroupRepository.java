package com.example.issatc.Infrastructure.EntityMappers;
import com.example.issatc.Entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaGroupRepository extends JpaRepository<GroupMapper,Integer> {
    @Query(value = "SELECT new com.example.issatc.Entities.Group(g.id,g.groupName,:sectorId)  FROM GroupMapper g WHERE g.id IN (SELECT s.group.id FROM student s WHERE s.sector.name =:sectorId)")
    List<Group> getGroupsBySector(@Param("sectorId") String sectorId);

   @Query(value = "select distinct sector_id from student where group_id =:id ",nativeQuery = true)
    String getSectorNameById(@Param("id") int id);

@Query("select count(*) from student s where s.group.id =:groupId and s.sector.name =:sectorId")
    int groupExistsInSector(@Param("groupId") int groupId, @Param("sectorId")String sectorId);
}
