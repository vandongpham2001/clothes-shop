package com.example.clothesshop.controller.admin;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.RoleDTO;
import com.example.clothesshop.dto.request.RoleRequest;
import com.example.clothesshop.service.IRoleService;
import com.example.clothesshop.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "roleApiOfAdmin")
@RequestMapping(path = "api/admin/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping()
    public ResponseEntity<RoleDTO> save(@RequestBody RoleRequest role) {
        if (role.getStatus()==null)
        {
            role.setStatus(SystemConstant.ACTIVE_STATUS);
        }
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role").toUriString());
        return ResponseEntity.created(uri).body(roleService.save(role));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<RoleDTO> pageRoles;
        List<RoleDTO> roles;
        sortable = PagingUtils.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageRoles = roleService.findAllPageable(pageable);
            roles = pageRoles.getContent();
            response.put("currentPage", pageRoles.getNumber() + 1);
            response.put("totalItems", pageRoles.getTotalElements());
            response.put("totalPages", pageRoles.getTotalPages());
        } else {
            roles = roleService.findAll(sortable);
        }
        response.put("roles", roles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return roleService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public RoleDTO detail(@PathVariable("id") Long id){
        return roleService.findById(id);
    }
}
