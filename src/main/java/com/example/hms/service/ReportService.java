package com.example.hms.service;

import com.example.hms.dto.patient.AddPatientDTO;
import com.example.hms.dto.report.AddReportDTO;
import com.example.hms.dto.report.ReportDTO;
import com.example.hms.mapper.ReportMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Doctor;
import com.example.hms.model.Patient;
import com.example.hms.model.Report;
import com.example.hms.repo.DoctorRepo;
import com.example.hms.repo.PatientRepo;
import com.example.hms.repo.ReportRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepo repo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final ReportMapper reportMapper;

    public Report addReport(AddReportDTO report){
        return repo.save(reportMapper.toEntity(report));
    }

    public Report editReport(Integer id, AddReportDTO reportDTO){
        Optional<Report> optionalReport = repo.findById(id);
        if(optionalReport.isPresent()){
            Report report = optionalReport.get();
            report.setResult(reportDTO.getResult());
            report.setType(reportDTO.getType());
            report.setDiagnosis(reportDTO.getDiagnosis());
            report.setCode(reportDTO.getCode());

            Optional<Patient> optionalPatient = patientRepo.findById(reportDTO.getPatientId());
            if(optionalPatient.isPresent()){
                Patient patient = optionalPatient.get();
                report.setPatient(patient);
            }else{
                throw new RuntimeException("Patient with id "+reportDTO.getPatientId()+" not found!");
            }

            Optional<Doctor> optionalDoctor = doctorRepo.findById(reportDTO.getDoctorId());
            if(optionalDoctor.isPresent()){
                Doctor doctor = optionalDoctor.get();
                report.setDoctor(doctor);
            }else{
                throw new RuntimeException("Doctor with id "+reportDTO.getDoctorId()+" not found!");
            }
            return repo.save(report);
        }else{
            throw new EntityNotFoundException("Report with id "+id+" not found!");
        }
    }

    public Iterable<ReportDTO> displayReports(){
        return repo.findAll().stream().map(report -> reportMapper.toDTO(report)).collect(Collectors.toList());
    }

    public ReportDTO findById(Integer id){
        return repo.findById(id).map(report -> reportMapper.toDTO(report)).get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
