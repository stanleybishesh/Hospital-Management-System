package com.example.hms.dto.administration;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class AddAdministrationDTO {
    private String name;
    private String phone;
    private String email;
    private String password;
}
