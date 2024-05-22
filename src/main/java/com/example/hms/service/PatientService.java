package com.example.hms.service;

import com.example.hms.dto.medicine.AddMedicineDTO;
import com.example.hms.dto.patient.AddPatientDTO;
import com.example.hms.dto.patient.PatientDTO;
import com.example.hms.mapper.PatientMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Medicine;
import com.example.hms.model.Patient;
import com.example.hms.repo.MedicineRepo;
import com.example.hms.repo.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepo repo;
    private final MedicineRepo medicineRepo;
    private final PatientMapper patientMapper;

    public Patient addPatient(AddPatientDTO patient){
        List<Medicine> medicineList = new ArrayList<>();
        Patient patient1 = patientMapper.toEntity(patient);

        for(Integer id:patient.getMedicineIds()){
            Medicine medicine = medicineRepo.findById(id).get();
            medicineList.add(medicine);
        }
        patient1.setMedicines(medicineList);
        return repo.save(patient1);
    }

    public Patient editPatient(Integer id, AddPatientDTO addPatientDTO){
        List<Medicine> medicineList = new ArrayList<>();
        Optional<Patient> optionalPatient = repo.findById(id);
        if(optionalPatient.isPresent()){
            Patient patient = optionalPatient.get();
            patient.setName(addPatientDTO.getName());
            patient.setEmail(addPatientDTO.getEmail());
            patient.setPhone(addPatientDTO.getPhone());
            patient.setPassword(addPatientDTO.getPassword());

            for(Integer medicineId: addPatientDTO.getMedicineIds()){
                Medicine medicine = medicineRepo.findById(medicineId).get();
                medicineList.add(medicine);
            }
            patient.setMedicines(medicineList);
            return repo.save(patient);
        }else{
            throw new EntityNotFoundException("Patient with id "+id+" not found!");
        }
    }

    public Iterable<PatientDTO> displayPatients(){
        List<PatientDTO> patientDTOList = new ArrayList<>();
        for (Patient patient: repo.findAll()){
            PatientDTO patientDTO = patientMapper.toDTO(patient);
            patientDTOList.add(patientDTO);
        }
        return patientDTOList;
    }

    public PatientDTO findById(Integer id){
        Optional<Patient> patient = repo.findById(id);
        return patient.map(p->patientMapper.toDTO(p)).get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
