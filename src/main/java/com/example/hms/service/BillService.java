package com.example.hms.service;

import com.example.hms.dto.bill.AddBillDTO;
import com.example.hms.dto.bill.BillDTO;
import com.example.hms.mapper.BillMapper;
import com.example.hms.model.Bill;
import com.example.hms.model.Report;
import com.example.hms.repo.BillRepo;
import com.example.hms.repo.ReportRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BillService {
    private final BillRepo billRepo;
    private final ReportRepo reportRepo;
    private final BillMapper billMapper;

    public BillDTO addBill(AddBillDTO dto){
        Bill bill = billMapper.toEntity(dto);
        return billMapper.toDTO(billRepo.save(bill));
    }

    public BillDTO editBill(Integer id, AddBillDTO addBillDTO){
        Optional<Bill> optionalBill = billRepo.findById(id);
        if(optionalBill.isPresent()){
            Bill bill = optionalBill.get();
            Optional<Report> optionalReport = reportRepo.findById(addBillDTO.getReportId());
            if(optionalReport.isPresent()){
                Report report = optionalReport.get();
                bill.setReport(report);
            }
            bill.setAmount(addBillDTO.getAmount());
            bill.setDate(addBillDTO.getDate());
            return billMapper.toDTO(billRepo.save(bill));
        }else{
            throw new EntityNotFoundException("Bill with id "+id+" not found!");
        }
    }

    public Iterable<BillDTO> displayBills(){
        return billRepo.findAll().stream().map(b -> billMapper.toDTO(b)).collect(Collectors.toList());
    }

    public BillDTO findById(Integer id){
        Optional<Bill> optionalBill = billRepo.findById(id);
        return optionalBill.map(bill -> billMapper.toDTO(bill)).get();
    }

    public void deleteAll(){
        billRepo.deleteAll();
    }

    public void deleteById(Integer id){
        billRepo.deleteById(id);
    }
}
