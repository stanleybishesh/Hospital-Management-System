package com.example.hms.service;

import com.example.hms.model.Appointment;
import com.example.hms.model.User;
import com.example.hms.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;

    public User addUser(User user){
        return repo.save(user);
    }

    public Iterable<User> displayUsers(){
        return repo.findAll();
    }

    public Optional<User> findById(Long id){
        return repo.findById(id);
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }
}
