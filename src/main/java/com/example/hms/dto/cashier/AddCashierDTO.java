package com.example.hms.dto.cashier;

import com.example.hms.dto.patient.PatientDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
public class AddCashierDTO {
    private String name;
    private Double amount;
    private LocalDateTime date;
    private Integer patientId;
}
