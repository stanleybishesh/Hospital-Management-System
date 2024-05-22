package com.example.hms.dto.ward;

import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.patient.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardDTO {
    private Integer id;
    private String name;
    private String type;
    private Integer capacity;
    private Integer occupancy;
    private Integer availability;
    private Collection<DoctorDTO> doctors;
    private Collection<PatientDTO> patients;
}
