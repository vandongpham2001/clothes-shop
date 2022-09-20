package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.UserDTO;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLoginPage(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/user";
    }

    @GetMapping("/user")
    public String getUserPage(Model model){
        UserDTO user = new UserDTO();
        user.setName("ADMIN");
        model.addAttribute("user", user);
        return "user";
    }
}
