package com.example.hms.service;

import com.example.hms.dto.administration.AddAdministrationDTO;
import com.example.hms.dto.administration.AdministrationDTO;
import com.example.hms.mapper.AdministrationMapper;
import com.example.hms.model.Administration;
import com.example.hms.repo.AdministrationRepo;
import com.example.hms.security.WebSecurityConfig;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministrationService {
    private final AdministrationRepo repo;
    private final AdministrationMapper administrationMapper;
    private final WebSecurityConfig web;

    public Administration addAdministration(AddAdministrationDTO administration){
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String encryptedPassword = bCryptPasswordEncoder.encode(administration.getPassword());
//        administration.setPassword(encryptedPassword);
        String encryptedPwd = web.passwordEncoder().encode(administration.getPassword());
        administration.setPassword(encryptedPwd);
        return repo.save(administrationMapper.toEntity(administration));
    }

    public Administration editAdministration(Integer id, AddAdministrationDTO administrationDTO){
        Optional<Administration> administration = repo.findById(id);
        if(administration.isPresent()){
            Administration admin = administration.get();
            admin.setName(administrationDTO.getName());
            admin.setEmail(administrationDTO.getEmail());
            admin.setPhone(administrationDTO.getPhone());
            admin.setPassword(web.passwordEncoder().encode(administrationDTO.getPassword()));
            return repo.save(admin);
        }else{
            throw new EntityNotFoundException("Admin Not Found !");
        }
    }

    public List<AdministrationDTO> displayAdmins(){
        List<AdministrationDTO> administrationDTOList = new ArrayList<>();
        for (Administration administration: repo.findAll()){
            AdministrationDTO administrationDTO = administrationMapper.toDTO(administration);
            administrationDTOList.add(administrationDTO);
        }
        return administrationDTOList;
    }

    public AdministrationDTO findById(Integer id){
        Optional<Administration> optionalAdministration = repo.findById(id);

//(Another way ) return optionalAdministration.map(administrationMapper::toDTO).get();
        return optionalAdministration.map(a -> administrationMapper.toDTO(a)).get();
    }

    public void deleteAll(){
        repo.deleteAll();
    }

    public void deleteById(Integer id){
        repo.deleteById(id);
    }

}
