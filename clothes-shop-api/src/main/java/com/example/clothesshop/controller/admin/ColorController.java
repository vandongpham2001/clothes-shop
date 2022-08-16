package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.service.IColorService;
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
@RequestMapping(path = "api/admin/color")
public class ColorController {
    @Autowired
    IColorService colorService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ColorDTO> pageColors;
        List<ColorDTO> colors;
        sortable = PagingUtil.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageColors = colorService.findAllPageable(pageable);
            colors = pageColors.getContent();
            response.put("currentPage", pageColors.getNumber() + 1);
            response.put("totalItems", pageColors.getTotalElements());
            response.put("totalPages", pageColors.getTotalPages());
        } else {
            colors = colorService.findAll(sortable);
        }
        response.put("colors", colors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ColorDTO create(@RequestBody ColorDTO dto) throws IOException {
        return colorService.save(dto);
    }

    @PutMapping
    public ColorDTO update(@RequestBody ColorDTO dto) throws IOException {
        return colorService.save(dto);
    }

    @DeleteMapping
    public void delete(@RequestBody long[] ids) {
        colorService.delete(ids);
    }
}
