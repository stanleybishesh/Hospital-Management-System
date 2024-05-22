package com.example.hms.dto.administration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministrationDTO {
    private Integer id;
    private String name;
    private String phone;
    private String email;
}
