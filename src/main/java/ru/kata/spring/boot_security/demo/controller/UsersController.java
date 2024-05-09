package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.CustomUserDetailsService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public UsersController(UserService userService, CustomUserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping()
    public String findOne(ModelMap model, Principal principal) {
        User user = userDetailsService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
