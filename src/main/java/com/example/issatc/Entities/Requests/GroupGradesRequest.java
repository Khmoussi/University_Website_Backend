package com.example.issatc.Entities.Requests;

import com.example.issatc.Entities.NumInscWithNote;
import lombok.Data;

import java.util.List;

@Data
public class GroupGradesRequest {
    int groupId;
   int subjectId;
   String sectorId;
   String teacherMail;
   List<NumInscWithNote> list;
}
