package com.example.hms.service;

import com.example.hms.dto.patient.AddPatientDTO;
import com.example.hms.dto.ward.AddWardDTO;
import com.example.hms.dto.ward.WardDTO;
import com.example.hms.mapper.WardMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Doctor;
import com.example.hms.model.Patient;
import com.example.hms.model.Ward;
import com.example.hms.repo.DoctorRepo;
import com.example.hms.repo.PatientRepo;
import com.example.hms.repo.WardRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WardService {
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final WardRepo repo;
    private final WardMapper wardMapper;

    public Ward addWard(AddWardDTO ward){
        List<Doctor> doctorList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();

        Ward ward1 = new Ward();
        ward1=wardMapper.toEntity(ward);

        for(Integer id: ward.getDoctorIds()){
            Doctor doctor = doctorRepo.findById(id).get();
            doctorList.add(doctor);
        }
        for(Integer id: ward.getPatientIds()){
            Patient patient = patientRepo.findById(id).get();
            patientList.add(patient);
        }
        ward1.setDoctors(doctorList);
        ward1.setPatients(patientList);

        return repo.save(ward1);
    }

    public Ward editWard(Integer id, AddWardDTO addWardDTO){
        List<Doctor> doctorList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();
        Optional<Ward> optionalWard = repo.findById(id);
        if(optionalWard.isPresent()){
            Ward ward = optionalWard.get();
            ward.setName(addWardDTO.getName());
            ward.setType(addWardDTO.getType());
            ward.setCapacity(addWardDTO.getCapacity());
            ward.setOccupancy(addWardDTO.getOccupancy());
            ward.setAvailability(addWardDTO.getAvailability());

            for(Integer patientId: addWardDTO.getPatientIds()){
                Patient patient = patientRepo.findById(patientId).get();
                patientList.add(patient);
            }
            for(Integer doctorId: addWardDTO.getDoctorIds()){
                Doctor doctor = doctorRepo.findById(doctorId).get();
                doctorList.add(doctor);
            }
            ward.setPatients(patientList);
            ward.setDoctors(doctorList);
            return repo.save(ward);
        }else{
            throw new EntityNotFoundException("Ward with id "+id+" not found!");
        }
    }

    public Iterable<WardDTO> displayWards(){
        return repo.findAll().stream().map(ward -> wardMapper.toDTO(ward)).collect(Collectors.toList());
    }

    public WardDTO findById(Integer id){
        return repo.findById(id).map(ward -> wardMapper.toDTO(ward)).get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
