package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class AdminController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public AdminController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping(value = "/posts")
    //@GetMapping
    public String findAll(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping(value = "/user")
    public String findOne(ModelMap model, Principal principal) {
        User user = userDetailsService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/new")
    public String viewCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/posts";
    }

    @GetMapping("/edit")
    public String getUser(Model model, @RequestParam long id) {
        model.addAttribute("user", userService.findOne(id));
        return "edit";
    }

    @PostMapping("/posts")
    public String update(@ModelAttribute("user") User user, @RequestParam long id) {
        userService.update(id, user);
        return "redirect:/posts";
    }

    @PostMapping("/users/delete")
    public String delete(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/posts";
    }
    @GetMapping(value = "/index")
    public String getStartPageIndex() {
        return "index";
    }
}