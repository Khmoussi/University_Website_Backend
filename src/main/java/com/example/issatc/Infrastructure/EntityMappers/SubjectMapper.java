package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

enum SubjectType{
    COURS,
    TP,
    TD
}
@Entity
public class SubjectMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    // tp ,td,cours
    private SubjectType type;
    private int semester;

    @OneToMany(mappedBy = "subject")
    List<SeanceMapper> seances =new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")
    List<SectorMapper> sectors;

    @OneToMany(mappedBy = "subject")
    List<RecordMapper> records;

}
