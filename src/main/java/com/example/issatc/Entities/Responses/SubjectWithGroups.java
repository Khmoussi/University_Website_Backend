package com.example.issatc.Entities.Responses;

import com.example.issatc.Entities.Group;
import com.example.issatc.Entities.Subject;
import com.example.issatc.Infrastructure.EntityMappers.SubjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class SubjectWithGroups {
    private Subject subject;
    List<Group> groupList;

    public SubjectWithGroups(Subject i, List<Group> groupList) {
        this.groupList=groupList;
        this.subject=i;
    }
}
