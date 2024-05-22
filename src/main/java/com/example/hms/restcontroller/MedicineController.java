package com.example.hms.restcontroller;

import com.example.hms.dto.medicine.AddMedicineDTO;
import com.example.hms.model.Cashier;
import com.example.hms.model.Medicine;
import com.example.hms.service.MedicineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @PostMapping("/addMedicine")
    private Medicine addMedicine(@RequestBody AddMedicineDTO medicine){
        return medicineService.addMedicine(medicine);
    }

    @GetMapping("/displayMedicines")
    private Iterable<Medicine> displayMedicines(){
        return medicineService.displayMedicines();
    }

    @GetMapping("/displayMedicine/{id}")
    private Optional<Medicine> findById(@PathVariable Integer id){
        return medicineService.findById(id);
    }

    @DeleteMapping("/deleteAllMedicines")
    private void deleteAll(){
        medicineService.deleteAll();
    }

    @DeleteMapping("/deleteMedicine/{id}")
    private void deleteById(@PathVariable Integer id){
        medicineService.deleteById(id);
    }

    @PutMapping("/editMedicine/{id}")
    private ResponseEntity<Medicine> editMedicine(@PathVariable Integer id,@RequestBody AddMedicineDTO medicineDTO){
        Medicine medicine = medicineService.editMedicine(id, medicineDTO);
        if(medicine!=null){
            return new ResponseEntity<>(medicine,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
