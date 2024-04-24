package com.example.issatc.Infrastructure.EntityMappers;

import com.example.issatc.Entities.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity(name = "sector")
public class SectorMapper {

    @Id
    String name;


    @OneToMany(mappedBy = "sector")
    List<StudentMapper> students;


    @ManyToMany
            @JoinTable(name = "sector_subject",joinColumns = @JoinColumn(name = "sector_id"),inverseJoinColumns = @JoinColumn(name = "subject_id"))
    List<SubjectMapper>subjects;

    public SectorMapper(String name) {
        this.name = name;
    }
    public SectorMapper() {
    }
}
