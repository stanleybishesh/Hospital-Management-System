package com.example.hms.dto.patient;

import com.example.hms.dto.medicine.MedicineDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Builder(toBuilder = true)
@Data
public class AddPatientDTO {
    private Collection<Integer> medicineIds;
    private String name;
    private String email;
    private String phone;
    private String password;
}
