package com.example.hms.dto.appointment;

import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.patient.PatientDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Integer id;
    private PatientDTO patient;
    private DoctorDTO doctor;
    private String status;
    private String type;
    private LocalDateTime date;
}
