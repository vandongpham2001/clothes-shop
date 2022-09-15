package com.example.clothesshop.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.converter.UserConverter;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.entity.RoleEntity;
import com.example.clothesshop.entity.UserEntity;
import com.example.clothesshop.repository.RoleRepository;
import com.example.clothesshop.repository.UserRepository;
import com.example.clothesshop.service.IUserService;
import com.example.clothesshop.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDTO> findAllPageable(Integer status, Pageable pageable) {
        Page<UserDTO> results;
        Page<UserEntity> entities;
        if (status != null) {
            entities = userRepository.findByStatus(status, pageable);
        } else {
            entities = userRepository.findAll(pageable);
        }
        results = userConverter.mapEntityPageIntoDtoPage(entities, UserDTO.class);
        return results;
    }

    @Override
    public List<UserDTO> findAll(Integer status, Sort sort) {
        List<UserDTO> results;
        Iterable<UserEntity> entities;
        if (status != null) {
            entities = userRepository.findByStatus(status, sort);
        } else {
            entities = userRepository.findAll(sort);
        }
        results = ObjectMapperUtil.mapAll(IterableUtils.toList(entities), UserDTO.class);
        return results;
    }

    @Override
    public UserDTO findById(long id) {
        UserEntity entity = userRepository.findById(id).get();
        return userConverter.toDTO(entity);
    }

    @Override
    public UserDTO findByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username);
        if (entity != null)
            return userConverter.toDTO(entity);
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserEntity entity = userRepository.findByEmail(email);
        if (entity != null)
            return userConverter.toDTO(entity);
        return null;
    }

    @Override
    public UserDTO save(UserDTO dto) {
        UserEntity entity = new UserEntity();
        UserEntity userByUsername = userRepository.findByUsername(dto.getUsername());
        UserEntity userByEmail = userRepository.findByEmail(dto.getEmail());
        if (userByEmail != null) {
            throw new IllegalStateException("email had taken");
        }
        if (userByUsername != null) {
            throw new IllegalStateException("username had taken");
        }
        if (dto.getPassword() != null) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getId() != null) {
            UserEntity old_entity = userRepository.findById(dto.getId()).get();
            entity = userConverter.toEntity(dto, old_entity);
        } else {
            entity = userConverter.toEntity(dto);
        }
        return userConverter.toDTO(userRepository.save(entity));

    }

    @Override
    public String delete(long[] ids) {
        for (long id : ids) {
            UserEntity exists = userRepository.findById(id).get();
            if (exists != null) {
                exists.setStatus(SystemConstant.INACTIVE_STATUS);
                userRepository.save(exists);
            }
        }
        return "deleted";
    }

    @Override
    public UserDTO addRoleToUser(String username, String roleName) {
        UserEntity user = userRepository.findByUsername(username);
        RoleEntity role = roleRepository.findByName(roleName);
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
    public UserDTO getCurrentUserLogin() {
        return null;
    }

    @Override
    public UserDTO getUserFromJWT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDTO user = new UserDTO();
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String access_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(access_token);
                String username = decodedJWT.getSubject();
                user = getUser(username);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
            return user;
        } else {
            throw new RuntimeException("Access token is missing");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
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
