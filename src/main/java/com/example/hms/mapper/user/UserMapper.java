package com.example.hms.mapper.user;

import com.example.hms.common.Preconditions;
import com.example.hms.dto.user.UUserDto;
import com.example.hms.dto.user.UserUpdateDto;
import com.example.hms.mapper.role.RoleMapper;
import com.example.hms.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UUserDto toDto(User entity) {

        return new UUserDto().toBuilder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .enabled(entity.isEnabled())
                .isEmailVerified(entity.isEmailVerified())
                .userType(entity.getUserType())
                .deviceToken(entity.getDeviceToken())
                .roles(Preconditions.checkNotNull(entity.getRoles()) ? roleMapper.toDto(entity.getRoles().stream().collect(Collectors.toList())) : null)
                .role(Preconditions.checkNotNull(entity.getRoles()) ? entity.getRoles().stream().findFirst().get().getName().substring(entity.getRoles().stream().findFirst().get().getName().indexOf("_") + 1) : null)
                .build();
    }

    public UserUpdateDto toUserUpdateDto(User entity) {
        if(Preconditions.checkNull(entity))
            return null;

        return new UserUpdateDto().toBuilder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .enabled(entity.isEnabled())
                .isEmailVerified(entity.isEmailVerified())
                .userType(entity.getUserType())
                .roles(Preconditions.checkNotNull(entity.getRoles()) ? roleMapper.toDto(entity.getRoles().stream().collect(Collectors.toList())) : null)
                .role(Preconditions.checkNotNull(entity.getRoles()) ? entity.getRoles().stream().findFirst().get().getName().substring(entity.getRoles().stream().findFirst().get().getName().indexOf("_") + 1) : null)
                .build();
    }


    public User toEntity(UUserDto dto) {
        return new User().toBuilder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .enabled(dto.getEnabled())
                .isEmailVerified(dto.getIsEmailVerified())
                .build();
    }

    public List<UUserDto> toDto(List<User> userList) {
        List<UUserDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            dtoList.add(toDto(user));
        }
        return dtoList;
    }

//    public PaginationRecordsDto<UUserDto> toDto(Page<User> entityList) {
//        List<UUserDto> dtoList = new ArrayList<>();
//        for (User entity : entityList.getContent()) {
//            dtoList.add(toDto(entity));
//        }
//
//        return new PaginationRecordsDto<UUserDto>().toBuilder()
//                .paginationInfo(new PaginationInfoDto().toBuilder()
//                        .currentPage(entityList.getNumber())
//                        .totalItems(entityList.getTotalElements())
//                        .totalPages(entityList.getTotalPages())
//                        .size(entityList.getSize())
//                        .numberOfItems(entityList.getNumberOfElements())
//                        .isFirst(entityList.isFirst())
//                        .isLast(entityList.isLast())
//                        .hasNext(entityList.hasNext())
//                        .hasPrevious(entityList.hasPrevious())
//                        .build())
//                .data(dtoList)
//                .build();
//
//    }


}
