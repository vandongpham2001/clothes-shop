package com.example.clothesshop.controller.admin;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.dto.request.OrderRequest;
import com.example.clothesshop.service.IOrderService;
import com.example.clothesshop.utils.PagingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController(value = "apiOrderOfAdmin")
@CrossOrigin
@RequestMapping(path = "api/admin/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<OrderDTO> pageOrders;
        List<OrderDTO> orders;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageOrders = orderService.findAllPageable(status, pageable);
            orders = pageOrders.getContent();
            response.put("currentPage", pageOrders.getNumber() + 1);
            response.put("totalItems", pageOrders.getTotalElements());
            response.put("totalPages", pageOrders.getTotalPages());
        } else {
            orders = orderService.findAll(status, sortable);
        }
        response.put("orders", orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/user/{username}")
    public ResponseEntity<Map<String, Object>> getByUsername(@PathVariable("username") String username,
                                                                @RequestParam(value = "page", required = false) Integer page,
                                                                @RequestParam(value = "limit", required = false) Integer limit,
                                                                @RequestParam(value = "sort", required = false, defaultValue = "desc") String sort,
                                                                @RequestParam(value = "status", required = false) Integer status) {
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
//        response.put("orders", "order");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public OrderDTO create(@RequestBody OrderRequest dto) {
//        OrderDTO dto = new OrderDTO();
//        dto = ObjectMapperUtils.map(request, OrderDTO.class);
        dto.setStatus(SystemConstant.ORDER_WAIT_FOR_CONFIRM_STATUS);
        return orderService.save(dto);
    }

    @PutMapping
    public OrderDTO update(@RequestBody OrderRequest dto) {
//        OrderDTO dto = new OrderDTO();
//        dto = ObjectMapperUtils.map(request, OrderDTO.class);
        return orderService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody Long[] ids) {
        return orderService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public OrderDTO detail(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }
}
