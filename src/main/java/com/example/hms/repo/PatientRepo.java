package com.example.hms.repo;

import com.example.hms.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PatientRepo extends JpaRepository<Patient,Integer> {
}
