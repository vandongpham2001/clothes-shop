package com.example.clothesshop.controller.web;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.dto.UserDTO;
import com.example.clothesshop.dto.request.OrderRequest;
import com.example.clothesshop.service.IOrderService;
import com.example.clothesshop.service.IUserService;
import com.example.clothesshop.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "apiOrderOfWeb")
@CrossOrigin
@RequestMapping(path = "api/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IUserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable("username") String username,
                                                      @RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status,
                                                      HttpServletRequest servletRequest,
                                                      HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(username);
        if (userJWT.getId() == userDTO.getId()) {
            Map<String, Object> response = new HashMap<>();
            Pageable pageable;
            Sort sortable;
            Page<OrderDTO> pageOrders;
            List<OrderDTO> orders;
            sortable = PagingUtils.sortByCreatedDate(sort);
            if (page != null && limit != null) {
                pageable = PageRequest.of(page - 1, limit, sortable);
                pageOrders = orderService.findPageableByUsername(username, status, pageable);
                orders = pageOrders.getContent();
                response.put("currentPage", pageOrders.getNumber() + 1);
                response.put("totalItems", pageOrders.getTotalElements());
                response.put("totalPages", pageOrders.getTotalPages());
            } else {
                orders = orderService.findByUsername(username, status, sortable);
            }
            response.put("orders", orders);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return null;
    }

    @PostMapping("/{username}")
    public OrderDTO create(@PathVariable("username") String username,
                           @RequestBody OrderRequest dto,
                           HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(username);
        if (userJWT.getId() == userDTO.getId()) {
            dto.setUser_id(userJWT.getId());
            dto.setStatus(SystemConstant.ORDER_WAIT_FOR_CONFIRM_STATUS);
            return orderService.save(dto);
        }
        return null;
    }

    @GetMapping("/{username}/{id}")
    public OrderDTO detail(@PathVariable("username") String username,
                           @PathVariable("id") Long id,
                           HttpServletRequest servletRequest,
                           HttpServletResponse servletResponse) throws IOException {
        UserDTO userJWT = userService.getUserFromJWT(servletRequest, servletResponse);
        UserDTO userDTO = userService.findByUsername(username);
        if (userJWT.getId() == userDTO.getId()) {
            return orderService.findById(id);
        }
        return null;
    }
}
