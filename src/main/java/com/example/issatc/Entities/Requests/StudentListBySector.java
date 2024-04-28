package com.example.issatc.Entities.Requests;

import lombok.Data;

@Data
public class StudentListBySector {
    String sectorId;

    public StudentListBySector(String sectorId) {
        this.sectorId = sectorId;
    }
}
