package com.example.hms.repo;

import com.example.hms.dto.patient.PatientDTO;
import com.example.hms.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface MedicineRepo extends JpaRepository<Medicine,Integer> {
//    Medicine findByMedicineIds(Collection<Integer> id);
}
