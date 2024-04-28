package com.example.issatc.Infrastructure.EntityMappers.Request;

import lombok.Data;

@Data
public class GroupScheduleRequest {
    int groupID;

    public GroupScheduleRequest(int groupID) {
        this.groupID = groupID;
    }
}
