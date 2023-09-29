package ru.viktor.booksBoot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.viktor.booksBoot.security.UserDetails;

@Controller
public class UserController {
    @GetMapping("/showUserInfo")
    public String showUserInfo(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails personDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("user", personDetails.getUser());
        return "/users/show";
    }
}
