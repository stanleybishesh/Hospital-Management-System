package com.example.hms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UUserDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private Boolean enabled;

    private Boolean isEmailVerified;

    private Boolean isUserUpdateDetails;

    private Collection<RRoleDto> roles;

    private String role;

    private String userType;

    private String deviceToken;
}
