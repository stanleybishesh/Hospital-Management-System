package com.example.hms.mapper;

import com.example.hms.dto.bill.AddBillDTO;
import com.example.hms.dto.bill.BillDTO;
import com.example.hms.dto.doctor.AddDoctorDTO;
import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.dto.report.AddReportDTO;
import com.example.hms.dto.report.ReportDTO;
import com.example.hms.model.Bill;
import com.example.hms.model.Report;
import com.example.hms.repo.DoctorRepo;
import com.example.hms.repo.PatientRepo;
import com.example.hms.repo.ReportRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class ReportMapper {
    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;

    public Report toEntity(AddReportDTO dto){
        return new Report().toBuilder()
                .date(dto.getDate())
                .type(dto.getType())
                .code(dto.getCode())
                .result(dto.getResult())
                .diagnosis(dto.getDiagnosis())
                .doctor(doctorRepo.findById(dto.getDoctorId()).get())
                .patient(patientRepo.findById(dto.getPatientId()).get())
                .build();
    }
    public ReportDTO toDTO(Report report){
        return new ReportDTO().toBuilder()
                .id(report.getId())
                .type(report.getType())
                .date(report.getDate())
                .code(report.getCode())
                .result(report.getResult())
                .diagnosis(report.getDiagnosis())
                .doctor(doctorMapper.toDTO(report.getDoctor()))
                .patient(patientMapper.toDTO(report.getPatient()))
                .build();
    }
}
