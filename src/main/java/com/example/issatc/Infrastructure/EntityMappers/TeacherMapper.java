package com.example.issatc.Infrastructure.EntityMappers;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "teacher")
@Inheritance(strategy = InheritanceType.JOINED)

public class TeacherMapper extends UserMapper {
    @Column(unique = true)
    long cin;


    // reation with Seance
    @OneToMany(mappedBy = "teacher")
    List<SeanceMapper> seances =new ArrayList<>();
    @OneToMany(mappedBy = "teacher")
    List<RecordMapper> records;




























  //constructors
    public TeacherMapper() {
    }

    public TeacherMapper(String email, String firstName, String lastName, String password, Role role, long phoneNumb, long cin) {
        super(email, firstName, lastName, password, role, phoneNumb);
        this.cin = cin;
    }
    public TeacherMapper(String email, String firstName, String lastName,  Role role, long cin) {
        super(email, firstName, lastName,  role);
        this.cin = cin;
    }

}
