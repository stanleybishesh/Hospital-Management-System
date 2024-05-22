package com.example.hms.dto.appointment;

import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.patient.PatientDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
public class AddAppointmentDTO {
    private Integer patientId;
    private Integer doctorId;
    private String status;
    private String type;
    private LocalDateTime date;
}
