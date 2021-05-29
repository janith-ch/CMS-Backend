package com.af.cms.controller;

import com.af.cms.model.User;
import com.af.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    //endpoint for register user
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    //endpoint get all users
    @GetMapping("/")
    public List<User> users() {
        return userService.users();
    }

    //endpoint for get single user
    @GetMapping("/user")
    public Optional<User> user(@RequestParam String id) {
        return userService.getSingleUser(id);
    }

    // endpoint for update user
    @PutMapping("/{id}")
    public boolean editUser(@PathVariable String id, @RequestBody User user) {
        return userService.editUser(user, id);
    }

    // endpoint for delete user
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    //get user list according to status
    @PostMapping("/list")
    public List<User>userWithStatus(@RequestParam String status[]){
        return userService.userWithStatus(status);
    }
    //update userRole
    @PutMapping("/request-user-role/{id}")
    public boolean editRequestUserRole(@PathVariable String id, @RequestBody User user) {
        return userService.editRequestUserRole(user, id);
    }
    @PutMapping("/user-role/{id}")
    public boolean editUserRole(@PathVariable String id, @RequestBody User user) {
        return userService.editUserRole(user, id);
    }

}



