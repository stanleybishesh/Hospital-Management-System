package com.example.hms.mapper;

import com.example.hms.dto.administration.AddAdministrationDTO;
import com.example.hms.dto.administration.AdministrationDTO;
import com.example.hms.model.Administration;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class AdministrationMapper {
    public Administration toEntity(AddAdministrationDTO administrationDTO){
        return new Administration().toBuilder()
                .name(administrationDTO.getName())
                .email(administrationDTO.getEmail())
                .phone(administrationDTO.getPhone())
                .password(administrationDTO.getPassword())
                .build();
    }

    public AdministrationDTO toDTO(Administration administration){
        return new AdministrationDTO().toBuilder()
                .id(administration.getId())
                .name(administration.getName())
                .email(administration.getEmail())
                .phone(administration.getPhone())
                .build();
    }
}
