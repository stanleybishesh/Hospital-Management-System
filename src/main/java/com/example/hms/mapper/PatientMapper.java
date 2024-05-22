package com.example.hms.mapper;

import com.example.hms.dto.patient.AddPatientDTO;
import com.example.hms.dto.patient.PatientDTO;
import com.example.hms.model.Medicine;
import com.example.hms.model.Patient;
import com.example.hms.repo.MedicineRepo;
import com.example.hms.repo.PatientRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Data
@RequiredArgsConstructor
public class PatientMapper {
    private final MedicineRepo medicineRepo;
    private final MedicineMapper medicineMapper;

    public Patient toEntity(AddPatientDTO patientDTO){
        return new Patient().toBuilder()
                .email(patientDTO.getEmail())
                .phone(patientDTO.getPhone())
                .name(patientDTO.getName())
                .password(patientDTO.getPassword())
//(Wrong way!)  .medicines((Collection<Medicine>) medicineRepo.findByMedicineIds(patientDTO.getMedicineId()))
                .build();
    }

    public PatientDTO toDTO(Patient patient){
        return new PatientDTO().toBuilder()
                .id(patient.getId())
                .name(patient.getName())
                .email(patient.getEmail())
                .phone(patient.getPhone())
                .medicines(patient.getMedicines().stream().map(medicine -> medicineMapper.toDTO(medicine)).collect(Collectors.toList()))
//(Another way) .medicines(patient.getMedicines().stream().map(medicineMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
