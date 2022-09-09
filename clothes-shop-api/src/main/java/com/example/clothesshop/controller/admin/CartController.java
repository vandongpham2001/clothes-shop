package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.CartDTO;
import com.example.clothesshop.service.ICartService;
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

@RestController(value = "apiCartOfAdmin")
@RequestMapping(path = "api/admin/cart")
public class CartController {
    @Autowired
    private ICartService cartService;
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<CartDTO> pageCarts;
        List<CartDTO> carts;
        sortable = PagingUtil.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageCarts = cartService.findAllPageable(pageable);
            carts = pageCarts.getContent();
            response.put("currentPage", pageCarts.getNumber() + 1);
            response.put("totalItems", pageCarts.getTotalElements());
            response.put("totalPages", pageCarts.getTotalPages());
        } else {
            carts = cartService.findAll(sortable);
        }
        response.put("carts", carts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public CartDTO create(@ModelAttribute CartDTO dto){
        return cartService.save(dto);
    }

    @PutMapping
    public CartDTO update(@ModelAttribute CartDTO dto){
        return cartService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids) {
        cartService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public CartDTO detail(@PathVariable("id") Long id){
        return cartService.findById(id);
    }
}
