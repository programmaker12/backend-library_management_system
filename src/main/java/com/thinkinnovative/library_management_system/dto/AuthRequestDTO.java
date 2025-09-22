package com.thinkinnovative.library_management_system.dto;

import com.thinkinnovative.library_management_system.entity.User;
import lombok.Data;

@Data
public class AuthRequestDTO {
    private String username;
    private String password;
    private User.Role role;
    private Integer memberId;
}
