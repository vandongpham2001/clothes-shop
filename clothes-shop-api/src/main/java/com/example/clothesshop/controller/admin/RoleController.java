package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController(value = "roleApiOfAdmin")
@RequestMapping(path = "api/admin/role")
public class RoleController {
    @Autowired
    IRoleService roleService;

    @PostMapping()
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }
}
