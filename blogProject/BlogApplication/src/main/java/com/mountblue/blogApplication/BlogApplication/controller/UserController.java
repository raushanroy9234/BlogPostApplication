package com.mountblue.blogApplication.BlogApplication.controller;

import com.mountblue.blogApplication.BlogApplication.model.User;
import com.mountblue.blogApplication.BlogApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registerForm")
    public String registerForm(Model theModel){
        User theUser = new User();
        theModel.addAttribute("user",theUser);
        return "register";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User theUser) {
        userService.save(theUser);
        return "redirect:/users/registerForm";
    }
}
