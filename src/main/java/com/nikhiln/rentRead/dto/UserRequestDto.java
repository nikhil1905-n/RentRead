package com.nikhiln.rentRead.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

import com.nikhiln.rentRead.entity.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

}
