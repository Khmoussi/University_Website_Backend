package com.example.issatc.Infrastructure.EntityMappers.Request;

import com.example.issatc.Entities.Requests.GroupsBySectorRequest;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Data
@RequiredArgsConstructor
public class AssigningGroupsBySectorRequest {

    String sectorId;
    List<GroupsBySectorRequest> list ;
}
