package com.example.hms.service;

import com.example.hms.dto.appointment.AddAppointmentDTO;
import com.example.hms.dto.appointment.AppointmentDTO;
import com.example.hms.mapper.AppointmentMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Doctor;
import com.example.hms.model.Patient;
import com.example.hms.repo.AppointmentRepo;
import com.example.hms.repo.DoctorRepo;
import com.example.hms.repo.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepo repo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final AppointmentMapper appointmentMapper;

    public Appointment addAppointment(AddAppointmentDTO dto){
        return repo.save(appointmentMapper.toEntity(dto));
    }

    public Appointment editAppointment(Integer id, AddAppointmentDTO appointmentDTO){
        Optional<Appointment> optionalAppointment = repo.findById(id);
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(appointmentDTO.getStatus());
            appointment.setType(appointmentDTO.getType());
            appointment.setDate(appointmentDTO.getDate());

            Optional<Patient> optionalPatient = patientRepo.findById(appointmentDTO.getPatientId());
            if(optionalPatient.isPresent()){
                Patient patient = optionalPatient.get();
                appointment.setPatient(patient);
            }else{
                throw new EntityNotFoundException("Patient with id "+appointmentDTO.getPatientId()+" not found!");
            }

            Optional<Doctor> optionalDoctor = doctorRepo.findById(appointmentDTO.getDoctorId());
            if(optionalDoctor.isPresent()){
                Doctor doctor = optionalDoctor.get();
                appointment.setDoctor(doctor);
            }else{
                throw new EntityNotFoundException("Doctor with id "+appointmentDTO.getDoctorId()+" not found!");
            }
            return repo.save(appointment);
        }else{
            throw new EntityNotFoundException("Appointment with id "+id+" not found!");
        }
    }

    public Iterable<AppointmentDTO> displayAppointments(){
        List<AppointmentDTO> appointmentDTOList = new ArrayList<>();
        for(Appointment appointment: repo.findAll()){
            AppointmentDTO appointmentDTO = appointmentMapper.toDTO(appointment);
            appointmentDTOList.add(appointmentDTO);
        }
        return appointmentDTOList;
    }

    public AppointmentDTO findById(Integer id){
        Optional<Appointment> appointment = repo.findById(id);
        return appointment.map(a->appointmentMapper.toDTO(a)).get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
