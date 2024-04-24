package com.example.issatc.Infrastructure.EntityMappers;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class ClassRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    private String name;

    public ClassRoom(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public ClassRoom() {

    }
    @OneToMany(mappedBy = "classRoom")
    List<SeanceMapper> seanceMapperList;
}
