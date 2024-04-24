package com.example.issatc.Infrastructure.EntityMappers.Request;

import lombok.Data;

@Data
public class TeacherWithDepRequest {
    private  String lastName;
    private  String firstName;
    private  String email;
    private  long phoneNum;
    private String departmentName;
    private long cin;
    public TeacherWithDepRequest(String lastName, String firstName, String email, long phoneNum , long cin,String departmentName ) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.cin=cin;
        this.departmentName=departmentName;
    }

}
