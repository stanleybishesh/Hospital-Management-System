package com.example.hms.service;

import com.example.hms.model.role.Role;
import com.example.hms.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo repo;

    public Role addRole(Role role){
        return repo.save(role);
    }

    public Iterable<Role> displayRoles(){
        return repo.findAll();
    }

    public Optional<Role> findById(Long id){
        return repo.findById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }
}
