package com.example.issatc.Entities.Requests;

import com.example.issatc.Infrastructure.EntityMappers.SubjectType;
import lombok.Data;

@Data
public class SubjectAbsence {
    String subjectName;
    private SubjectType type;
    private int semester;
    int absenceNumb;

    public SubjectAbsence(String subjectName, int absenceNumb,SubjectType type, int semester) {
        this.subjectName = subjectName;
        this.type = type;
        this.semester = semester;
        this.absenceNumb = absenceNumb;
    }
}
