package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "record")
@IdClass(RecordMapper.RecordId.class)

public class RecordMapper {
    private int absenceNum;
    private int noteNum;

    @Id
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherMapper teacher;
    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectMapper subject;
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentMapper student;
public static class RecordId implements Serializable{
    private TeacherMapper teacher;
    private SubjectMapper subject;
    private StudentMapper student;
    private int absenceNum;
    private int noteNum;




}

}
