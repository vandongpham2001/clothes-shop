package com.example.clothesshop.controller.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.*;
import com.example.clothesshop.dto.request.LoginRequest;
import com.example.clothesshop.dto.response.JwtResponse;
import com.example.clothesshop.repository.CartRepository;
import com.example.clothesshop.service.ICartService;
import com.example.clothesshop.service.IUserService;
import com.example.clothesshop.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController(value = "userApiOfWeb")
@RequestMapping(path = "api/")
//@Api(tags = {SwaggerConstant.API_TAG})
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICartService cartService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;

//    @PostMapping("/login")
//    public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
//
//    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO user) {
        user.setStatus(SystemConstant.ACTIVE_STATUS);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user").toUriString());
        ResponseEntity<UserDTO> result = ResponseEntity.created(uri).body(userService.save(user));
        userService.addRoleToUser(user.getUsername(), SystemConstant.USER_ROLE);
        return result;
    }

    @GetMapping("/user/{user}/cart")
    public ResponseEntity<Map<String, Object>> getAllByUsername(@PathVariable("user") String user,
                                                                @RequestParam(value = "page", required = false) Integer page,
                                                                @RequestParam(value = "limit", required = false) Integer limit,
                                                                HttpServletRequest servletRequest,
                                                                HttpServletResponse servletResponse) throws IOException {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Page<CartDTO> pageCarts;
        List<CartDTO> carts;
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(user);
        if (userJWT.getId() == userDTO.getId()) {
            if (page != null && limit != null) {
                pageable = PageRequest.of(page - 1, limit);
                pageCarts = cartService.findAllByUsername(user, pageable);
                carts = pageCarts.getContent();
                response.put("currentPage", pageCarts.getNumber() + 1);
                response.put("totalItems", pageCarts.getTotalElements());
                response.put("totalPages", pageCarts.getTotalPages());
            } else {
                carts = cartService.findAllByUsername(user);
            }
            response.put("carts", carts);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/user/{user}/cart")
    @ApiOperation(value = "Add a new product to cart", notes = "Add a new product information into the system", response = CartDTO.class)
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Product was added to cart successfully"),
            @ApiResponse(responseCode = "500", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "480", description = "The request is malformed or invalid"),
            @ApiResponse(responseCode = "404", description = "The resource URL was not found on the server"),
            @ApiResponse(responseCode = "403", description = "You are not authorized . Please authenticate and try again"),
            @ApiResponse(responseCode = "401", description = "You don't have permission to this resource")})
    public CartDTO addProductToCart(@PathVariable("user") String user,
                                    @RequestBody CartDTO dto,
                                    HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(user);
        if (userJWT.getId() == userDTO.getId()) {
            dto.setUser_id(userJWT.getId());
            return cartService.save(dto);
        }
        return null;
    }

    @PutMapping("/user/{user}/cart")
    public CartDTO updateProductInCart(@PathVariable("user") String user,
                                       @RequestBody CartDTO dto,
                                       HttpServletRequest servletRequest,
                                       HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(user);
        if (userJWT.getId() == userDTO.getId()) {
            dto.setUser_id(userJWT.getId());
            return cartService.save(dto);
        }
        return null;
    }

    @DeleteMapping("/user/{user}/cart")
    public String removeProductFromCart(@PathVariable("user") String user,
                                         @RequestBody Long[] ids,
                                         HttpServletRequest servletRequest,
                                         HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(user);
        if (userJWT.getId() == userDTO.getId()) {
            return cartService.delete(ids);
        }
        return null;
    }

    @DeleteMapping("/user/{user}/cart/deleteAll")
    public List<CartDTO> deleteCartByUsername(@PathVariable("user") String user,
                                            HttpServletRequest servletRequest,
                                            HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(user);
        if (userJWT.getId() == userDTO.getId()) {
            return cartService.deleteCartByUsername(user);
        }
        return null;
    }

    @GetMapping("/user/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                Algorithm algorithm = Algorithm.HMAC512("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserDTO user = userService.getUser(username);
                String access_token = jwtUtils.generateAccessToken(request, user);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", "Bearer " + access_token);
                tokens.put("refresh_token", "Bearer " + refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }

    @GetMapping("/user/getUserJWT")
    public UserDTO getUserFromJWT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return userService.getUserFromJWT(request, response);
    }

//    @GetMapping(value = "/{user}")
//    public UserDTO detail(@PathVariable("user") String user){
//        return userService.findByUsername(user);
//    }
}
