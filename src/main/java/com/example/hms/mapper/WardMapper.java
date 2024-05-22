package com.example.hms.mapper;

import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.ward.AddWardDTO;
import com.example.hms.dto.ward.WardDTO;
import com.example.hms.model.Doctor;
import com.example.hms.model.Patient;
import com.example.hms.model.Ward;
import com.example.hms.repo.DoctorRepo;
import com.example.hms.repo.PatientRepo;
import com.example.hms.repo.WardRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@Data
@RequiredArgsConstructor
public class WardMapper {
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public Ward toEntity(AddWardDTO wardDTO){
        return new Ward().toBuilder()
                .name(wardDTO.getName())
                .type(wardDTO.getType())
                .availability(wardDTO.getAvailability())
                .capacity(wardDTO.getCapacity())
                .occupancy(wardDTO.getOccupancy())
                .build();
    }

    public WardDTO toDTO(Ward ward){
        return new WardDTO().toBuilder()
                .id(ward.getId())
                .name(ward.getName())
                .type(ward.getType())
                .availability(ward.getAvailability())
                .capacity(ward.getCapacity())
                .occupancy(ward.getOccupancy())
                .doctors(ward.getDoctors().stream().map(doctor -> doctorMapper.toDTO(doctor)).collect(Collectors.toList()))
                .patients(ward.getPatients().stream().map(patient -> patientMapper.toDTO(patient)).collect(Collectors.toList()))
                .build();
    }
}
