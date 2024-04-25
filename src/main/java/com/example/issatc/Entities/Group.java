package com.example.issatc.Entities;

import lombok.Data;

@Data
public class Group {
    int id;
    String name;
    String sectorName;


    public Group(int id, String name,String sectorName) {
        this.id = id;
        this.name = name;
        this.sectorName=sectorName;
    }
    public Group() {

    }
}
