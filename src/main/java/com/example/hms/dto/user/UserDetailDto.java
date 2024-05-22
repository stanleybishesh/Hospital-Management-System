package com.example.hms.dto.user;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDetailDto {
    private String age;
    private String bodyType;
    private String height;
    private String weight;
    private String gender;
    private Long activityId;
    private String phone;
    private String maintenanceCalories;
    private List<Long> diseasesId;
}
