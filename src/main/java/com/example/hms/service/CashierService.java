package com.example.hms.service;

import com.example.hms.dto.cashier.AddCashierDTO;
import com.example.hms.dto.cashier.CashierDTO;
import com.example.hms.mapper.CashierMapper;
import com.example.hms.model.Appointment;
import com.example.hms.model.Cashier;
import com.example.hms.model.Patient;
import com.example.hms.repo.CashierRepo;
import com.example.hms.repo.PatientRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashierService {
    private final CashierRepo repo;
    private final PatientRepo patientRepo;
    private final CashierMapper cashierMapper;

    public Cashier addCashier(AddCashierDTO cashier){
        return repo.save(cashierMapper.toEntity(cashier));
    }

    public Cashier editCashier(Integer id, AddCashierDTO addCashierDTO){
        Optional<Cashier> optionalCashier = repo.findById(id);
        if(optionalCashier.isPresent()) {
            Cashier cashier = optionalCashier.get();
            cashier.setName(addCashierDTO.getName());
            cashier.setDate(addCashierDTO.getDate());
            cashier.setAmount(addCashierDTO.getAmount());

            Optional<Patient> optionalPatient = patientRepo.findById(addCashierDTO.getPatientId());
            if(optionalPatient.isPresent()){
                Patient patient = optionalPatient.get();
                cashier.setPatient(patient);
            }
            return repo.save(cashier);
        }else{
            throw new EntityNotFoundException("Cashier with id "+id+" not found!");
        }
    }

    public Iterable<Cashier> displayCashiers(){
        return repo.findAll();
    }

    public Optional<Cashier> findById(Integer id){
        return repo.findById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }
}
