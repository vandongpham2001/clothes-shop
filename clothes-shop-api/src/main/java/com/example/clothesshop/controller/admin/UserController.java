package com.example.clothesshop.controller.admin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.dto.RoleToUser;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.service.IUserService;
import com.example.clothesshop.util.PagingUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "userApiOfAdmin")
@RequestMapping(path = "api")
public class UserController {
    @Autowired
    private IUserService userService;

//    @GetMapping("/admin/user")
//    public ResponseEntity<List<UserDTO>> getUsers() {
//        return ResponseEntity.ok().body(userService.getUsers());
//    }

    @GetMapping("/admin/user")
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<UserDTO> pageUsers;
        List<UserDTO> users;
        sortable = PagingUtil.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageUsers = userService.findAllPageable(status, pageable);
            users = pageUsers.getContent();
            response.put("currentPage", pageUsers.getNumber() + 1);
            response.put("totalItems", pageUsers.getTotalElements());
            response.put("totalPages", pageUsers.getTotalPages());
        } else {
            users = userService.findAll(status, sortable);
        }
        response.put("users", users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/admin/user")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user").toUriString());
        return ResponseEntity.created(uri).body(userService.save(user));
    }

    @DeleteMapping("/admin/user")
    public void delete(@RequestBody long[] ids) {
        userService.delete(ids);
    }

    @PostMapping("/admin/user/add-role-to-user")
    public ResponseEntity<?> saveRoleToUser(@RequestBody RoleToUser form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/admin/user/{id}")
    public UserDTO detail(@PathVariable("id") Long id){
        return userService.findById(id);
    }

//    @GetMapping("/user/token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
//            try {
//                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(refresh_token);
//                String username = decodedJWT.getSubject();
//                UserDTO user = userService.getUser(username);
//                String access_token = JWT.create()
//                        .withSubject(user.getUsername())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
//                        .withIssuer(request.getRequestURL().toString())
//                        .withClaim("roles", user.getRoles().stream().map(RoleDTO::getName).collect(Collectors.toList()))
//                        .sign(algorithm);
//                Map<String, String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//            } catch (Exception exception){
//                response.setHeader("error", exception.getMessage());
//                response.setStatus(FORBIDDEN.value());
//                Map<String, String> error = new HashMap<>();
//                error.put("error_message", exception.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//        } else {
//            throw new RuntimeException("Refresh token is missing");
//        }
//    }
}
