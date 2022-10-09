package com.example.clothesshop.controller.web;

import com.example.clothesshop.dto.UserDetailsImpl;
import com.example.clothesshop.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudinary.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
    public ModelAndView getUserPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            mav.setViewName("redirect:/login");
        } else {
            mav.setViewName("user");
            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            JwtUtils jwtUtils = new JwtUtils();
            String access_token = null;
            access_token = "Bearer " + jwtUtils.generateAccessToken(request, user);;
//            System.out.println("ui user: " + access_token);
//            System.out.println("ui user: " + user.getUsername());
//            System.out.println("ui user: " + user.getName());
//            System.out.println("ui user: " + user.getEmail());
//            System.out.println("ui user: " + user.getPhone());
//            System.out.println("ui user: " + user.getAddress());
            mav.addObject("user", user);
            mav.addObject("token", access_token);
        }
        return mav;
    }
}


