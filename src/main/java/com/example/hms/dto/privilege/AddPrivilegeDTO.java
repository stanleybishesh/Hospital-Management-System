package com.example.hms.dto.privilege;

import com.example.hms.dto.role.RoleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder(toBuilder = true)
@Data
public class AddPrivilegeDTO {
    private String name;
    private Collection<Long> roleId;
}
