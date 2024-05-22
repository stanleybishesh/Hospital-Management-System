package com.example.hms.restcontroller;

import com.example.hms.dto.bill.AddBillDTO;
import com.example.hms.dto.bill.BillDTO;
import com.example.hms.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bill")
public class BillController {
    private final BillService billService;

    @PostMapping("/addBill")
    private ResponseEntity<?> addBill(@RequestBody AddBillDTO bill){
        return new ResponseEntity<>(billService.addBill(bill),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/displayBills")
    private ResponseEntity<?> displayBills(){
        return new ResponseEntity<>(billService.displayBills(),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/displayBill/{id}")
    private ResponseEntity<?> findById(@PathVariable Integer id){
        return new ResponseEntity<>(billService.findById(id),new HttpHeaders(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllBills")
    private void deleteAll(){
        billService.deleteAll();
    }

    @DeleteMapping("/deleteBill/{id}")
    private void deleteById(Integer id){
        billService.deleteById(id);
    }

    @PutMapping("/editBill/{id}")
    private ResponseEntity<?> editBill(@PathVariable Integer id, @RequestBody AddBillDTO addBillDTO){
        BillDTO bill = billService.editBill(id, addBillDTO);
        if(bill!=null){
            return new ResponseEntity<>(bill,new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
