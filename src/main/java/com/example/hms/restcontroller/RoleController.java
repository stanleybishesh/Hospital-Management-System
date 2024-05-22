package com.example.hms.restcontroller;

import com.example.hms.dto.role.AddRoleDTO;
import com.example.hms.model.role.Role;
import com.example.hms.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/addRole")
    private Role addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @GetMapping("/displayRoles")
    private Iterable<Role> displayRoles(){
        return roleService.displayRoles();
    }

    @GetMapping("/displayRole/{id}")
    private Optional<Role> findById(@PathVariable Long id){
        return roleService.findById(id);
    }

    @DeleteMapping("/deleteAllRoles")
    private void deleteAll(){
        roleService.deleteAll();
    }

    @DeleteMapping("/deleteRole/{id}")
    private void deleteById(@PathVariable Long id){
        roleService.deleteById(id);
    }

//    @PutMapping("/editRole/{id}")
//    private ResponseEntity<Role> editRole(@PathVariable Integer id,@RequestBody Role role){
//        Optional<Role> existingRoleOption = roleService.findById(id);
//        if(existingRoleOption.isPresent()){
//            Role existingRole = existingRoleOption.get();
//            existingRole.setName(role.getName());
//            Role editedRole = roleService.addRole(existingRole);
//            return ResponseEntity.ok(editedRole);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
}
