package com.example.issatc.Entities.Responses;

import com.example.issatc.Entities.Subject;
import lombok.Data;

@Data
public class SubjectWithNote {
    Subject subject;
    Integer grade;

    public SubjectWithNote(Subject subject, Integer grade) {
        this.subject = subject;
        this.grade = grade;
    }
    public SubjectWithNote(Subject subject) {
        this.subject = subject;

    }
}
