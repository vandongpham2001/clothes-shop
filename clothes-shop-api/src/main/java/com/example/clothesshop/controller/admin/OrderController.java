package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.OrderDTO;
import com.example.clothesshop.service.IOrderService;
import com.example.clothesshop.util.PagingUtil;
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
@RequestMapping(path = "api/admin/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<OrderDTO> pageOrders;
        List<OrderDTO> orders;
        sortable = PagingUtil.sort(sort);
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

    @PostMapping
    public OrderDTO create(@ModelAttribute OrderDTO dto) {
        return orderService.save(dto);
    }

    @PutMapping
    public OrderDTO update(@ModelAttribute OrderDTO dto) {
        return orderService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return orderService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public OrderDTO detail(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }
}
