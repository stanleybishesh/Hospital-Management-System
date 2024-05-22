package com.example.hms.dto.role;

import com.example.hms.dto.privilege.PrivilegeDTO;
import com.example.hms.dto.user.UUserDto;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.UUID;

@Builder(toBuilder = true)
@Data
public class AddRoleDTO {
    private UUID uuid;
    private String name;
    private Collection<Long> userId;
    private Collection<Long> privilegeId;
}
