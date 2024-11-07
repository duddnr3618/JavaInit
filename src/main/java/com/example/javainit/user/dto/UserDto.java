package com.example.javainit.user.dto;

import com.example.javainit.user.entity.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    private String userEmail;
    private String password;
    private String userName;
    private String userPhone;
    private LocalDate createAt;
    private Role role;
}
