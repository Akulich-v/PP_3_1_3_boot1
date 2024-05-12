package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserEditController {
    private final UserService userService;

    @Autowired
    public UserEditController(UserService userService) {
        this.userService = userService;;
    }
    @GetMapping("/{userId}")
    public User findById(@PathVariable long userId) {
        return userService.findOne(userId);
    }
    @PostMapping
    public boolean create(@RequestBody User user) {
        userService.update(user);
        return true;
    }
}
