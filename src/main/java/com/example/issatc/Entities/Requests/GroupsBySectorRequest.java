package com.example.issatc.Entities.Requests;

import lombok.Data;

import java.util.List;

@Data
public class GroupsBySectorRequest {

    private String groupName;
    private List<Long> listStudents;

}
