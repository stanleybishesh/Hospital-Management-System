package com.example.hms.restcontroller;

import com.example.hms.model.Cashier;
import com.example.hms.model.User;
import com.example.hms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/addUser")
    private User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/displayUsers")
    private Iterable<User> displayUsers(){
        return userService.displayUsers();
    }

    @GetMapping("/displayUser/{id}")
    private Optional<User> findById(@PathVariable Long id){
        return userService.findById(id);
    }

    @DeleteMapping("/deleteAllUsers")
    private void deleteAll(){
        userService.deleteAll();
    }

    @DeleteMapping("/deleteUser/{id}")
    private void deleteById(@PathVariable Long id){
        userService.deleteById(id);
    }

//    @PutMapping("/editUser/{id}")
//    private ResponseEntity<User> editUser(@PathVariable Integer id,@RequestBody User user){
//        Optional<User> existingUserOption = userService.findById(id);
//        if(existingUserOption.isPresent()){
//            User existingUser = existingUserOption.get();
//            existingUser.setName(user.getName());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPhone(user.getPhone());
//            existingUser.setAddress(user.getAddress());
//            existingUser.setPassword(user.getPassword());
//            User editedUser = userService.addUser(existingUser);
//            return ResponseEntity.ok(editedUser);
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }
}
