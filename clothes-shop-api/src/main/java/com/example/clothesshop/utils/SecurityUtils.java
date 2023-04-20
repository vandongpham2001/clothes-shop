package com.example.clothesshop.utils;

import com.example.clothesshop.dto.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    @SuppressWarnings("unchecked")
    public static List<String> getAuthorities(){
        List<String> results = new ArrayList<>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }

    public static UserDetailsImpl getPrincipal() {
        UserDetailsImpl myUser = (UserDetailsImpl) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }

    public static String makeUrl()
    {
//        return request.getRequestURL().toString() + "?" + request.getQueryString();
        HttpServletRequest request = null; // get the request object from somewhere, e.g. by autowiring
        String baseUrl = request.getRequestURL().toString();
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        baseUrl = baseUrl.replace(uri, "");
        baseUrl += contextPath;
        return baseUrl;
    }
}
