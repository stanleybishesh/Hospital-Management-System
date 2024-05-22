package com.example.hms.dto.report;

import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.patient.PatientDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
public class AddReportDTO {
    private String code;
    private String type;
    private String result;
    private String diagnosis;
    private LocalDateTime date;
    private Integer doctorId;
    private Integer patientId;
}
