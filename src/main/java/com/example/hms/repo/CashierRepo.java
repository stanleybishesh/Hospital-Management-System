package com.example.hms.repo;

import com.example.hms.model.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashierRepo extends JpaRepository<Cashier,Integer> {
    Cashier findByPatientId(Integer id);
}
