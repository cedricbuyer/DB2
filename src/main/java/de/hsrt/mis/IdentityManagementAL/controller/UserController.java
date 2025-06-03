package de.hsrt.mis.IdentityManagementAL.controller;

import de.hsrt.mis.IdentityManagementAL.model.UserEntity;
import de.hsrt.mis.IdentityManagementAL.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserEntity user) {
        return userService.login(user);
    }

    @GetMapping("/users")
    public List<UserEntity> getUsers() {return userService.getUsers();}
}
