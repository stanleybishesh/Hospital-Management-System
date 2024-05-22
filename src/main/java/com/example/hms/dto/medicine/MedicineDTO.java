package com.example.hms.dto.medicine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicineDTO {
    private Integer id;
    private String name;
    private String type;
    private String description;
    private Double price;
}
