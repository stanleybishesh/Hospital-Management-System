package com.example.hms.dto.doctor;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class AddDoctorDTO {
    private String name;
    private String email;
    private String phone;
    private String password;
}
