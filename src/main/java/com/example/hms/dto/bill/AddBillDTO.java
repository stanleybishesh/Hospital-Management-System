package com.example.hms.dto.bill;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Data
public class AddBillDTO {
    private Integer reportId;
    private Double amount;
    private LocalDateTime date;
}
