package com.example.hms.bootstrap;


import com.example.hms.model.User;
import com.example.hms.model.role.Privilege;
import com.example.hms.model.role.Role;
import com.example.hms.repo.RPrivilegeRepository;
import com.example.hms.repo.RoleRepo;
import com.example.hms.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepo userRepository;


    private final RoleRepo roleRepository;


    private final RPrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup)
            return;

        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege createPrivilege = createPrivilegeIfNotFound("CREATE_PRIVILEGE");
        Privilege updatePrivilege = createPrivilegeIfNotFound("UPDATE_PRIVILEGE");
        Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE_PRIVILEGE");

        List<Privilege> superadminPrivileges = Arrays.asList(readPrivilege, createPrivilege, updatePrivilege, deletePrivilege);
        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, createPrivilege, updatePrivilege);
        List<Privilege> staffPrivileges = Arrays.asList(readPrivilege, createPrivilege);
        List<Privilege> userPrivilige = Arrays.asList(readPrivilege, createPrivilege);

        Role superadminRole = createRoleIfNotFound("ROLE_SUPERADMIN", superadminPrivileges);
        Role userRole = createRoleIfNotFound("ROLE_USER", userPrivilige);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_MODERATOR", staffPrivileges);
        createRoleIfNotFound("ROLE_DOCTOR", staffPrivileges);
        createRoleIfNotFound("ROLE_CASHIER", staffPrivileges);
        createRoleIfNotFound("ROLE_PATIENT", Arrays.asList(readPrivilege));
        createRoleIfNotFound("ROLE_GUEST", Arrays.asList(readPrivilege));

        User superadmin = createUserIfNotFound(new User().toBuilder()
                .name("HMS")
                .username("hms")
                .email("info@hms.com")
                .password(passwordEncoder.encode("Password@1"))
                .enabled(true)
                .isEmailVerified(true)
                .userType("SUPERADMIN")
                .roles(Arrays.asList(superadminRole))
                .build());
        createUserIfNotFound(superadmin);

//        User user = createUserIfNotFound(new User().toBuilder()
//                .name("Muku Mytra")
//                .username("mukunda")
//                .email("muku@fitnessmytra.com")
//                .password(passwordEncoder.encode("Password@1"))
//                .enabled(true)
//                .isEmailVerified(true)
//                .userType("USER")
//                .roles(Arrays.asList(userRole))
//                .build());
//        createUserIfNotFound(user);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege().toBuilder()
                    .name(name)
                    .build();
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Optional<Role> role = roleRepository.findByName(name);
        if (!role.isPresent()) {
            Role entity = new Role().toBuilder()
                    .name(name)
                    .privileges(privileges)
                    .build();
            roleRepository.save(entity);
            return entity;
        }
        return role.get();
    }

    @Transactional
    User createUserIfNotFound(User entity) {

        Optional<User> user = userRepository.findByUsername(entity.getUsername());

        if(user.isPresent())
            return user.get();

        return userRepository.save(entity);
    }
}
