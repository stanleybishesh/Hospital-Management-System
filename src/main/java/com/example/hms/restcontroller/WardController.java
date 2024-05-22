package com.example.hms.restcontroller;

import com.example.hms.dto.ward.AddWardDTO;
import com.example.hms.dto.ward.WardDTO;
import com.example.hms.model.Cashier;
import com.example.hms.model.Ward;
import com.example.hms.service.WardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ward")
public class WardController {
    private final WardService wardService;

    @PostMapping("/addWard")
    private Ward addWard(@RequestBody AddWardDTO ward){
        return wardService.addWard(ward);
    }

    @GetMapping("/displayWards")
    private Iterable<WardDTO> displayWards(){
        return wardService.displayWards();
    }

    @GetMapping("/displayWard/{id}")
    private WardDTO findById(@PathVariable Integer id){
        return wardService.findById(id);
    }

    @DeleteMapping("/deleteAllWards")
    private void deleteAll(){
        wardService.deleteAll();
    }

    @DeleteMapping("/deleteWard/{id}")
    private void deleteById(@PathVariable Integer id){
        wardService.deleteById(id);
    }

    @PutMapping("/editWard/{id}")
    private ResponseEntity<?> editWard(@PathVariable Integer id,@RequestBody AddWardDTO addWardDTO){
        Ward ward = wardService.editWard(id, addWardDTO);
        if(ward!=null){
            return new ResponseEntity<>(ward,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
