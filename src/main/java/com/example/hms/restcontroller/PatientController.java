package com.example.hms.restcontroller;

import com.example.hms.dto.patient.AddPatientDTO;
import com.example.hms.dto.patient.PatientDTO;
import com.example.hms.model.Cashier;
import com.example.hms.model.Patient;
import com.example.hms.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    @PostMapping("/addPatient")
    private Patient addPatient(@RequestBody AddPatientDTO patient){
        return patientService.addPatient(patient);
    }

    @GetMapping("/displayPatients")
    private Iterable<PatientDTO> displayPatients(){
        return patientService.displayPatients();
    }

    @GetMapping("/displayPatient/{id}")
    private PatientDTO findById(@PathVariable Integer id){
        return patientService.findById(id);
    }

    @DeleteMapping("/deleteAllPatients")
    private void deleteAll(){
        patientService.deleteAll();
    }

    @DeleteMapping("/deletePatient/{id}")
    private void deleteById(@PathVariable Integer id){
        patientService.deleteById(id);
    }

    @PutMapping("/editPatient/{id}")
    private ResponseEntity<Patient> editPatient(@PathVariable Integer id,@RequestBody AddPatientDTO patientDTO){
        Patient patient = patientService.editPatient(id, patientDTO);
        if(patient!=null){
            return new ResponseEntity<>(patient,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
