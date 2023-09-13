package com.security1.dto;

import com.security1.model.Authority;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String password;
    private Set<Authority> authorities;
}
