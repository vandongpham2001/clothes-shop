package com.example.clothesshop.utils;

import com.example.clothesshop.dto.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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
}
