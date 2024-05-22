package com.example.hms.service;

import com.example.hms.dto.doctor.AddDoctorDTO;
import com.example.hms.dto.medicine.AddMedicineDTO;
import com.example.hms.mapper.MedicineMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Medicine;
import com.example.hms.repo.MedicineRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineService {
    private final MedicineRepo repo;
    private final MedicineMapper medicineMapper;

    public Medicine addMedicine(AddMedicineDTO medicine){
        return repo.save(medicineMapper.toEntity(medicine));
    }

    public Medicine editMedicine(Integer id, AddMedicineDTO addMedicineDTO){
        Optional<Medicine> optionalMedicine = repo.findById(id);
        if(optionalMedicine.isPresent()){
            Medicine medicine = optionalMedicine.get();
            medicine.setName(addMedicineDTO.getName());
            medicine.setType(addMedicineDTO.getType());
            medicine.setDescription(addMedicineDTO.getDescription());
            medicine.setPrice(addMedicineDTO.getPrice());
            return repo.save(medicine);
        }else{
            throw new EntityNotFoundException("Medicine with id "+id+" not found!");
        }
    }

    public Iterable<Medicine> displayMedicines(){
        return repo.findAll();
    }

    public Optional<Medicine> findById(Integer id){
        return repo.findById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
