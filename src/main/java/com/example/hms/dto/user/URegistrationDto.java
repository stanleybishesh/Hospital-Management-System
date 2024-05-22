package com.example.hms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class URegistrationDto {

    private String name;

    private String username;

    private String oldPassword;

    private String password;

    private String rePassword;

    private String email;

    private String userType;
    private String deviceToken;

    private Collection<String> role;
}
