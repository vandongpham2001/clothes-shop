package com.example.clothesshop.controller.admin;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.ColorDTO;
import com.example.clothesshop.dto.request.ColorRequest;
import com.example.clothesshop.service.IColorService;
import com.example.clothesshop.utils.ObjectMapperUtils;
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

@RestController
@RequestMapping(path = "api/admin/color")
@CrossOrigin
public class ColorController {
    @Autowired
    private IColorService colorService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<ColorDTO> pageColors;
        List<ColorDTO> colors;
        sortable = PagingUtils.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageColors = colorService.findAllPageable(status, pageable);
            colors = pageColors.getContent();
            response.put("currentPage", pageColors.getNumber() + 1);
            response.put("totalItems", pageColors.getTotalElements());
            response.put("totalPages", pageColors.getTotalPages());
        } else {
            colors = colorService.findAll(status, sortable);
        }
        response.put("colors", colors);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ColorDTO create(@RequestBody ColorRequest dto){
//        ColorDTO dto = new ColorDTO();
//        dto = ObjectMapperUtils.map(request, ColorDTO.class);
        dto.setStatus(SystemConstant.ACTIVE_STATUS);
        return colorService.save(dto);
    }

    @PutMapping
    public ColorDTO update(@RequestBody ColorRequest dto){
//        ColorDTO dto = new ColorDTO();
//        dto = ObjectMapperUtils.map(request, ColorDTO.class);
        return colorService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return colorService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public ColorDTO detail(@PathVariable("id") Long id){
        return colorService.findById(id);
    }
}
