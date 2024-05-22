package com.example.hms.restcontroller;

import com.example.hms.dto.cashier.AddCashierDTO;
import com.example.hms.dto.cashier.CashierDTO;
import com.example.hms.model.Administration;
import com.example.hms.model.Cashier;
import com.example.hms.service.CashierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cashier")
public class CashierController {
    private final CashierService cashierService;

    @PostMapping("/addCashier")
    private Cashier addCashier(@RequestBody AddCashierDTO cashier){
        return cashierService.addCashier(cashier);
    }

    @GetMapping("/displayCashiers")
    private Iterable<Cashier> displayCashiers(){
        return cashierService.displayCashiers();
    }

    @GetMapping("/displayCashier/{id}")
    private Optional<Cashier> findById(@PathVariable Integer id){
        return cashierService.findById(id);
    }

    @DeleteMapping("/deleteAllCashiers")
    private void deleteAll(){
        cashierService.deleteAll();
    }

    @DeleteMapping("/deleteCashier/{id}")
    private void deleteById(@PathVariable Integer id){
        cashierService.deleteById(id);
    }

    @PutMapping("/editCashier/{id}")
    private ResponseEntity<Cashier> editCashier(@PathVariable Integer id,@RequestBody AddCashierDTO cashierDTO){
        Cashier cashier = cashierService.editCashier(id, cashierDTO);
        if(cashier!=null){
            return new ResponseEntity<>(cashier,new HttpHeaders(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
