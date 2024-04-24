package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class GroupMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int id;
    private String groupName;

    @OneToMany(mappedBy = "group")
    List<SeanceMapper> seances =new ArrayList<>();

    @OneToMany(mappedBy = "group")
    List<StudentMapper> students=new ArrayList<>();

    // constructors


    public GroupMapper(String groupName) {
        this.groupName = groupName;
    }
    public GroupMapper() {
    }
}


