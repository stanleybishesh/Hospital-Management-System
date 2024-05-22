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
public class UserUpdateDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private Boolean enabled;

    private Boolean isEmailVerified;

    private Collection<RRoleDto> roles;

    private String role;

    private String userType;

    private UserDetailResponseDto userDetail;

}
