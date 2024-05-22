package com.example.hms.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
}
