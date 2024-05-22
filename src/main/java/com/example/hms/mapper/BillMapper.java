package com.example.hms.mapper;

import com.example.hms.dto.bill.AddBillDTO;
import com.example.hms.dto.bill.BillDTO;
import com.example.hms.model.Bill;
import com.example.hms.repo.ReportRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class BillMapper {
    private final ReportRepo repo;
    private final ReportMapper reportMapper;

    public Bill toEntity(AddBillDTO dto){
        return new Bill().toBuilder()
                .date(dto.getDate())
                .amount(dto.getAmount())
                .report(repo.findById(dto.getReportId()).get())
                .build();
    }

    public BillDTO toDTO(Bill bill){
        return new BillDTO().toBuilder()
                .id(bill.getId())
                .date(bill.getDate())
                .amount(bill.getAmount())
                .report(reportMapper.toDTO(bill.getReport()))
                .build();
    }
}
