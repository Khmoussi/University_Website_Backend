package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SubjectMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    // tp ,td,cours
    private SubjectType type;
    private int semester;
    private int coeff;
    private int sessionNumb;

    @OneToMany(mappedBy = "subject")
    List<SeanceMapper> seances =new ArrayList<>();

    @ManyToMany(mappedBy = "subjects")

    List<SectorMapper> sectors;

    @OneToMany(mappedBy = "subject")
    List<RecordMapper> records;




    @ManyToMany(mappedBy = "subjects")
    List<TeacherMapper> teachers;

    public SubjectMapper(int id, String name, SubjectType type, int semester) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.semester = semester;
    }
    public SubjectMapper(int id, String name, SubjectType type, int semester,int sessionNumb,int coeff) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.semester = semester;
        this.sessionNumb=sessionNumb;
        this.coeff=coeff;
    }

    public SubjectMapper() {
    }
}
