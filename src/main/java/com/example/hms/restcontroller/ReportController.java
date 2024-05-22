package com.example.hms.restcontroller;

import com.example.hms.dto.report.AddReportDTO;
import com.example.hms.dto.report.ReportDTO;
import com.example.hms.model.Cashier;
import com.example.hms.model.Report;
import com.example.hms.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;

    @PostMapping("/addReport")
    private Report addReport(@RequestBody AddReportDTO report){
        return reportService.addReport(report);
    }

    @GetMapping("/displayReports")
    private Iterable<ReportDTO> displayReports(){
        return reportService.displayReports();
    }

    @GetMapping("/displayReport/{id}")
    private ReportDTO findById(@PathVariable Integer id){
        return reportService.findById(id);
    }

    @DeleteMapping("/deleteAllReports")
    private void deleteAll(){
        reportService.deleteAll();
    }

    @DeleteMapping("/deleteReport/{id}")
    private void deleteById(@PathVariable Integer id){
        reportService.deleteById(id);
    }

    @PutMapping("/editReport/{id}")
    private ResponseEntity<Report> editReport(@PathVariable Integer id,@RequestBody AddReportDTO addReportDTO){
        Report report = reportService.editReport(id, addReportDTO);
        if(report!=null){
            return new ResponseEntity<>(report,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
