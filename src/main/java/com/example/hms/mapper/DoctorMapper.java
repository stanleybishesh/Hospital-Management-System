package com.example.hms.mapper;

import com.example.hms.dto.doctor.AddDoctorDTO;
import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.model.Doctor;
import com.example.hms.repo.DoctorRepo;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.print.Doc;

@Component
@RequiredArgsConstructor
@Data
public class DoctorMapper {
    public Doctor toEntity(AddDoctorDTO doctorDTO){
        return new Doctor().toBuilder()
                .name(doctorDTO.getName())
                .email(doctorDTO.getEmail())
                .phone(doctorDTO.getPhone())
                .password(doctorDTO.getPassword())
                .build();
    }

    public DoctorDTO toDTO(Doctor doctor){
        return new DoctorDTO().toBuilder()
                .id(doctor.getId())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .phone(doctor.getPhone())
                .build();
    }
}
