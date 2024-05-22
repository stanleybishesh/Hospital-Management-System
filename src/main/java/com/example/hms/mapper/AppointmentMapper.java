package com.example.hms.mapper;

import com.example.hms.dto.appointment.AddAppointmentDTO;
import com.example.hms.dto.appointment.AppointmentDTO;
import com.example.hms.model.Appointment;
import com.example.hms.repo.AppointmentRepo;
import com.example.hms.repo.DoctorRepo;
import com.example.hms.repo.PatientRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Data
@RequiredArgsConstructor
public class AppointmentMapper {
    private final DoctorRepo doctorRepo;
    private final DoctorMapper doctorMapper;
    private final PatientRepo patientRepo;
    private final PatientMapper patientMapper;

    public Appointment toEntity(AddAppointmentDTO appointmentDTO){
        return new Appointment().toBuilder()
                .date(appointmentDTO.getDate())
                .type(appointmentDTO.getType())
                .status(appointmentDTO.getStatus())
                .doctor(doctorRepo.findById(appointmentDTO.getDoctorId()).get())
                .patient(patientRepo.findById(appointmentDTO.getPatientId()).get())
                .build();
    }

    public AppointmentDTO toDTO(Appointment appointment){
        return new AppointmentDTO().toBuilder()
                .id(appointment.getId())
                .type(appointment.getType())
                .status(appointment.getStatus())
                .date(appointment.getDate())
                .patient(patientMapper.toDTO(appointment.getPatient()))
                .doctor(doctorMapper.toDTO(appointment.getDoctor()))
                .build();
    }
}
