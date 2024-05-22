package com.example.hms.mapper;

import com.example.hms.dto.medicine.AddMedicineDTO;
import com.example.hms.dto.medicine.MedicineDTO;
import com.example.hms.model.Medicine;
import com.example.hms.repo.MedicineRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class MedicineMapper {
    public Medicine toEntity(AddMedicineDTO medicineDTO){
        return new Medicine().toBuilder()
                .name(medicineDTO.getName())
                .type(medicineDTO.getType())
                .price(medicineDTO.getPrice())
                .description(medicineDTO.getDescription())
                .build();
    }

    public MedicineDTO toDTO(Medicine medicine){
        return new MedicineDTO().toBuilder()
                .id(medicine.getId())
                .name(medicine.getName())
                .description(medicine.getDescription())
                .price(medicine.getPrice())
                .type(medicine.getType())
                .build();
    }
}
