package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/getAll")
    public String findAll(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/viewCreateForm")
    public String viewCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/saveUser")
    public String save(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/getAll";
    }

    @GetMapping("/getUserEdit")
    public String getUser(Model model, @RequestParam long id) {
        model.addAttribute("user", userService.findOne(id));
        return "edit";
    }

    @PostMapping("/updateUser")
    public String update(@ModelAttribute("user") User user, @RequestParam long id) {
        userService.update(id, user);
        return "redirect:/admin/getAll";
    }

    @PostMapping("/deleteUser")
    public String delete(@RequestParam long id) {
        userService.delete(id);
        return "redirect:/admin/getAll";
    }
}