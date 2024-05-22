package com.example.hms.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponseDto {

    private String age;
    private String bodyType;
    private String height;
    private String weight;
    private String gender;
    private String phone;
    private String maintenanceCalories;
}
