package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity(name = "seance")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"group_id", "day", "seanceNumb"}),
        @UniqueConstraint(columnNames = { "day", "seanceNumb","teacher_id"})

})
@IdClass(SeanceMapper.SeanceId.class)
public class SeanceMapper {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupMapper group;

    @Id
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherMapper teacher;

    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubjectMapper subject;

    @Id
    @Column(name = "day")
    private String day;

    @Id
    @Column(name = "seanceNumb")
    private int seanceNumb;

    // Other attributes...

    // Getters and setters...

    // Inner class for composite key definition
    public static class SeanceId implements Serializable {
        private GroupMapper group;
        private TeacherMapper teacher;
        private SubjectMapper subject;
        private String day;
        private int seanceNumb;

        // Constructors, getters, setters, equals, and hashCode methods...
    }
}