package com.example.issatc.Entities;

import com.example.issatc.Infrastructure.EntityMappers.SubjectType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class Subject {
    private int id;

    private String name;
    // tp ,td,cours
    private SubjectType type;
    private int semester;
    private int coeff;
    private int sessionNumb;


    public Subject() {
    }

    public Subject(int id, String name, SubjectType type, int semester, int coeff,int sessionNumb) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.semester = semester;
        this.coeff = coeff;
        this.sessionNumb=sessionNumb;
    }
}
