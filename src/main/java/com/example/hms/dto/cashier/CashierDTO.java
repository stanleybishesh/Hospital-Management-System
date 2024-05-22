package com.example.hms.dto.cashier;

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
public class CashierDTO {
    private Integer id;
    private String name;
    private Double amount;
    private LocalDateTime date;
    private PatientDTO patient;
}
