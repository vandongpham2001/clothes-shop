package com.example.clothesshop.service.impl;

import com.example.clothesshop.converter.UserConverter;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.entity.RoleEntity;
import com.example.clothesshop.entity.UserEntity;
import com.example.clothesshop.repository.RoleRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.service.IUserService;
import com.example.clothesshop.util.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO save(UserDTO dto) {
        UserEntity entity = new UserEntity();
        UserEntity userByUsername = userRepository.findByUsername(dto.getUsername());
        UserEntity userByEmail = userRepository.findByEmail(dto.getEmail());
        if (userByEmail != null){
            throw new IllegalStateException("email had taken");
        }
        if (userByUsername != null){
            throw new IllegalStateException("username had taken");
        }
        if (dto.getPassword()!=null) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getId()!=null){
            UserEntity old_entity = userRepository.findById(dto.getId()).get();
            entity = userConverter.toEntity(dto, old_entity);
        } else {
            entity = userConverter.toEntity(dto);
        }
        return userConverter.toDTO(userRepository.save(entity));

    }

    @Override
    public UserDTO addRoleToUser(String username, String roleName) {
        UserEntity user = userRepository.findByUsername(username);
        System.out.println(user);
        RoleEntity role = roleRepository.findByName(roleName);
        System.out.println(role);
        user.addRole(role);
        UserEntity savedUser = userRepository.save(user);
        log.info("Adding role {} to user {}", role.getName(), user.getUsername());
        return userConverter.toDTO(savedUser);
    }

    @Override
    public UserDTO getUser(String username) {
        log.info("Fetching user {}", username);
        return userConverter.toDTO(userRepository.findByUsername(username));
    }

    @Override
    public List<UserDTO> getUsers() {
        Iterable<UserEntity> entities = userRepository.findAll();
        return ObjectMapperUtil.mapAll(IterableUtils.toList(entities), UserDTO.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
