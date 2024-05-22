package com.example.hms.dto.bill;

import com.example.hms.dto.report.ReportDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Integer id;
    private ReportDTO report;
    private Double amount;
    private LocalDateTime date;
}
