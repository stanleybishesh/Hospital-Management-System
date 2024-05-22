package com.example.hms.service;

import com.example.hms.dto.cashier.AddCashierDTO;
import com.example.hms.dto.doctor.AddDoctorDTO;
import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.mapper.DoctorMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Doctor;
import com.example.hms.repo.DoctorRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepo repo;
    private final DoctorMapper doctorMapper;

    public Doctor addDoctor(AddDoctorDTO doctor){
        return repo.save(doctorMapper.toEntity(doctor));
    }

    public Doctor editDoctor(Integer id, AddDoctorDTO addDoctorDTO){
        Optional<Doctor> optionalDoctor = repo.findById(id);
        if(optionalDoctor.isPresent()){
            Doctor doctor = optionalDoctor.get();
            doctor.setName(addDoctorDTO.getName());
            doctor.setEmail(addDoctorDTO.getEmail());
            doctor.setPhone(addDoctorDTO.getPhone());
            doctor.setPassword(addDoctorDTO.getPassword());
            return repo.save(doctor);
        }else{
            throw new EntityNotFoundException("Doctor with id "+id+" not found!");
        }
    }

    public Iterable<DoctorDTO> displayDoctors(){
        List<DoctorDTO> doctorDTOList = new ArrayList<>();
        for(Doctor doctor: repo.findAll()){
            DoctorDTO doctorDTO = doctorMapper.toDTO(doctor);
            doctorDTOList.add(doctorDTO);
        }
        return doctorDTOList;
    }

    public DoctorDTO findById(Integer id){
        Optional<Doctor> doctor = repo.findById(id);
        return doctor.map(d -> doctorMapper.toDTO(d)).get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
