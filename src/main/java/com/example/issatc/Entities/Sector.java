package com.example.issatc.Entities;

import lombok.Data;

@Data
public class Sector {
    String  sectorName;

    public Sector(String sectorName) {
        this.sectorName = sectorName;
    }
    public Sector() {
    }
}
