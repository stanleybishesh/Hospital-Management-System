package com.example.hms.repo;

import com.example.hms.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collection;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {
}
