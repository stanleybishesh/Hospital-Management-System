package com.example.hms.restcontroller;

import com.example.hms.dto.appointment.AddAppointmentDTO;
import com.example.hms.dto.appointment.AppointmentDTO;
import com.example.hms.model.Appointment;
import com.example.hms.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/addAppointment")
    private Appointment addAppointment(@RequestBody AddAppointmentDTO appointment){
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping("/displayAppointments")
    private Iterable<AppointmentDTO> displayAppointments(){
        return appointmentService.displayAppointments();
    }

    @GetMapping("/displayAppointment/{id}")
    private AppointmentDTO findById(@PathVariable Integer id){
        return appointmentService.findById(id);
    }

    @DeleteMapping("/deleteAllAppointments")
    private void deleteAll(){
        appointmentService.deleteAll();
    }

    @DeleteMapping("/deleteAppointment/{id}")
    private void deleteById(@PathVariable Integer id){
        appointmentService.deleteById(id);
    }

    @PutMapping("/editAppointment/{id}")
    private ResponseEntity<?> editAppointment(@PathVariable Integer id,@RequestBody AddAppointmentDTO appointmentDTO){
        Appointment appointment = appointmentService.editAppointment(id,appointmentDTO);
        if(appointment!=null){
            return new ResponseEntity<>(appointment,new HttpHeaders(),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,new HttpHeaders(),HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
