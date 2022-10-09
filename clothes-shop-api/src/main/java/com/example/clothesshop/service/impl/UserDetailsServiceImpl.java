package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.UserConverter;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.dto.UserDetailsImpl;
import com.example.clothesshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDTO user = userConverter.toDTO(userRepository.findByUsername(username));
//        if (user == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//        return UserDetailsImpl.build(user);
        UserDTO user = userConverter.toDTO(userRepository.findByUsername(username));

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        System.out.println("UserDetailsServiceImpl: " + user.getUsername());
        System.out.println("UserDetailsServiceImpl: " + user.getRoles().size());
        return new UserDetailsImpl(user);
    }
}
