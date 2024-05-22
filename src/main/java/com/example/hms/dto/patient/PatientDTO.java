package com.example.hms.dto.patient;

import com.example.hms.dto.medicine.MedicineDTO;
import com.example.hms.model.Medicine;
import lombok.*;

import java.util.Collection;

@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Integer id;
    private Collection<MedicineDTO> medicines;
    private String name;
    private String email;
    private String phone;
}
