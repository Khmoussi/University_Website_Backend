package com.example.issatc.Entities;

import lombok.Data;

@Data
public class Classroom {
    int id;
    String name;

    public Classroom(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Classroom() {

    }
}
