package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.service.ISizeService;
import com.example.clothesshop.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/admin/size")
public class SizeController {

    @Autowired
    ISizeService sizeService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<SizeDTO> pageSizes;
        List<SizeDTO> sizes;
        sortable = PagingUtil.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageSizes = sizeService.findAllPageable(pageable);
            sizes = pageSizes.getContent();
            response.put("currentPage", pageSizes.getNumber() + 1);
            response.put("totalItems", pageSizes.getTotalElements());
            response.put("totalPages", pageSizes.getTotalPages());
        } else {
            sizes = sizeService.findAll(sortable);
        }
        response.put("sizes", sizes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public SizeDTO create(@RequestBody SizeDTO dto) throws IOException {
        return sizeService.save(dto);
    }

    @PutMapping
    public SizeDTO update(@RequestBody SizeDTO dto) throws IOException {
        return sizeService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids) {
        sizeService.delete(ids);
    }

}
