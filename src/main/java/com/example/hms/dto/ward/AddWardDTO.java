package com.example.hms.dto.ward;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder(toBuilder = true)
@Data
public class AddWardDTO {
    private String name;
    private String type;
    private Integer capacity;
    private Integer occupancy;
    private Integer availability;
    private Collection<Integer> doctorIds;
    private Collection<Integer> patientIds;
}
