package com.example.issatc.Entities;

import lombok.Data;

@Data
public class Group {
    int id;
    String name;

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Group() {

    }
}
