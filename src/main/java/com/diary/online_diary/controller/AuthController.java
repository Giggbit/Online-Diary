package com.diary.online_diary.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            String name = oauth2User.getAttribute("name");
            String email = oauth2User.getAttribute("email");

            model.addAttribute("name", name != null ? name : "Unknown User");
            model.addAttribute("email", email != null ? email : "Unknown Email");
        } else {
            model.addAttribute("name", "Guest");
            model.addAttribute("email", "N/A");
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
