package com.example.hms.restcontroller;

import com.example.hms.dto.doctor.AddDoctorDTO;
import com.example.hms.dto.doctor.DoctorDTO;
import com.example.hms.model.Cashier;
import com.example.hms.model.Doctor;
import com.example.hms.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/addDoctor")
    private Doctor addDoctor(@RequestBody AddDoctorDTO doctor){
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/displayDoctors")
    private Iterable<DoctorDTO> displayDoctors(){
        return doctorService.displayDoctors();
    }

    @GetMapping("/displayDoctor/{id}")
    private DoctorDTO findById(@PathVariable Integer id){
        return doctorService.findById(id);
    }

    @DeleteMapping("/deleteAllDoctors")
    private void deleteAll(){
        doctorService.deleteAll();
    }

    @DeleteMapping("/deleteDoctor/{id}")
    private void deleteById(@PathVariable Integer id){
        doctorService.deleteById(id);
    }

    @PutMapping("/editDoctor/{id}")
    private ResponseEntity<Doctor> editDoctor(@PathVariable Integer id,@RequestBody AddDoctorDTO doctorDTO){
        Doctor doctor = doctorService.editDoctor(id, doctorDTO);
        if(doctor!=null){
            return new ResponseEntity<>(doctor,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
