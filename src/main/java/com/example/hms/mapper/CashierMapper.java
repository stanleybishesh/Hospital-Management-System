package com.example.hms.mapper;

import com.example.hms.dto.cashier.AddCashierDTO;
import com.example.hms.dto.cashier.CashierDTO;
import com.example.hms.model.Cashier;
import com.example.hms.repo.CashierRepo;
import com.example.hms.repo.PatientRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class CashierMapper {
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;

    public Cashier toEntity(AddCashierDTO cashierDTO){
        return new Cashier().toBuilder()
                .date(cashierDTO.getDate())
                .amount(cashierDTO.getAmount())
                .name(cashierDTO.getName())
                .patient(patientRepo.findById(cashierDTO.getPatientId()).get())
                .build();
    }

    public CashierDTO toDTO(Cashier cashier){
        return new CashierDTO().toBuilder()
                .id(cashier.getId())
                .name(cashier.getName())
                .amount(cashier.getAmount())
                .date(cashier.getDate())
                .patient(patientMapper.toDTO(cashier.getPatient()))
                .build();
    }
}
