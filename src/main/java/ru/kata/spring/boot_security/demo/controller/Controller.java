package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@org.springframework.stereotype.Controller
public class Controller {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public Controller(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/index")
    public String getStartPageIndex() {
        return "index";
    }
}