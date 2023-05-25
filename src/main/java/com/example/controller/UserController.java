package com.example.controller;


import com.example.entity.User;
import com.example.exception.UserOutputException;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "user/index";
    }

    @GetMapping("/address")
    public String getAddress(Model model, @RequestParam int id) throws UserOutputException {
        User address = userService.getUserById(id);
        model.addAttribute("address", address);
        return "user/address";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }

    @DeleteMapping("/delete")
    public String deleteAll() {
        userService.deleteAllUsers();
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String update(Model model, @PathVariable("id") int id) throws UserOutputException {
        model.addAttribute("person", userService.getUserById(id));
        return "user/new";
    }

    @GetMapping(value = "/new")
    public String userCreate(Model model, User user) {
        model.addAttribute("person", user);
        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") User user) {
        userService.addUser(user);
        return "redirect:/user";
    }
}