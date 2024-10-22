package com.example.issatc.Entities.Responses;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class TeacherWithDepResponse {
    private  String lastName;
    private  String firstName;
    private  String email;
    private  long phoneNum;
    private long cin;
    private int maxHours;
    private String departmentName;

    public TeacherWithDepResponse(String lastName, String firstName, String email, long phoneNum ,long cin, String departmentName,int maxHours) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cin=cin;
        this.departmentName=departmentName;
        this.maxHours=maxHours;
    }
    public TeacherWithDepResponse(String lastName, String firstName, String email, long phoneNum ,long cin ,int maxHours) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cin=cin;
        this.maxHours=maxHours;

    }
}
