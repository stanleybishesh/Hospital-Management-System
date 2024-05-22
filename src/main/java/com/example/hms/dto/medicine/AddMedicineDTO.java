package com.example.hms.dto.medicine;

import lombok.Builder;
import lombok.Data;

@Builder(toBuilder = true)
@Data
public class AddMedicineDTO {
    private String name;
    private String type;
    private String description;
    private Double price;
}
