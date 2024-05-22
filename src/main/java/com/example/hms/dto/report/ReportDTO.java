package com.example.hms.dto.report;

import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.patient.PatientDTO;
import com.example.hms.model.Doctor;
import com.example.hms.model.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private Integer id;
    private String code;
    private String type;
    private String result;
    private String diagnosis;
    private LocalDateTime date;
    private DoctorDTO doctor;
    private PatientDTO patient;
}
