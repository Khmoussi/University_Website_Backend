package com.example.issatc.Entities.Requests;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ResetRequest {
String email;

    public ResetRequest(String email) {
        this.email = email;
    }
}
