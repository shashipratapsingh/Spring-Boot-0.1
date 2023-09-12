package com.security1.dto;

import com.security1.model.Authority;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private Authority authority;
}
