package com.example.hms.restcontroller;

import com.example.hms.dto.administration.AddAdministrationDTO;
import com.example.hms.dto.administration.AdministrationDTO;
import com.example.hms.model.Administration;
import com.example.hms.repo.AdministrationRepo;
import com.example.hms.service.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/administration")
public class AdministrationController {
    private final AdministrationService administrationService;
    private final AdministrationRepo administrationRepo;

    @PostMapping("/addAdministration")
    private Administration addAdministration(@RequestBody AddAdministrationDTO administration){
        return administrationService.addAdministration(administration);
    }

    @GetMapping("/displayAdmins")
    private List<AdministrationDTO> displayAdmins(){
        return administrationService.displayAdmins();
    }

    @GetMapping("/displayAdmin/{id}")
    private AdministrationDTO findById(@PathVariable Integer id){
        return administrationService.findById(id);
    }

    @DeleteMapping("/deleteAllAdmins")
    private void deleteAll(){
        administrationService.deleteAll();
    }

    @DeleteMapping("/deleteAdmin/{id}")
    private void deleteById(@PathVariable Integer id){
        administrationService.deleteById(id);
    }

    @PutMapping("/editAdmin/{id}")
    private ResponseEntity<?> editAdministration(@PathVariable Integer id, @RequestBody AddAdministrationDTO administrationDTO){
        Administration administration = administrationService.editAdministration(id,administrationDTO);
        if(administration!=null){
            return new ResponseEntity<>(administration,new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
