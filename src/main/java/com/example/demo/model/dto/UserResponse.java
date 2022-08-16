package com.example.demo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserResponse {

    private String email;
    private LocalDateTime createdAt;

}
