package com.example.hms.repo;

import com.example.hms.model.role.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RPrivilegeRepository extends JpaRepository<Privilege, Long> {
    Privilege findByName(String name);
}
