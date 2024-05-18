package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserEditController {
    private final UserService userService;
    private final RoleRepository roleRepository;


    @Autowired
    public UserEditController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        ;
    }
    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        return userService.findOne(userId);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @PostMapping
    public boolean create(@RequestBody User user) {
        userService.update(user);
        return true;
    }
}
