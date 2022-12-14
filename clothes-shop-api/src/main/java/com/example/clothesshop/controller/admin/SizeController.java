package com.example.clothesshop.controller.admin;

import com.example.clothesshop.constant.SystemConstant;
import com.example.clothesshop.dto.SizeDTO;
import com.example.clothesshop.dto.request.SizeRequest;
import com.example.clothesshop.service.ISizeService;
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
@CrossOrigin
@RequestMapping(path = "api/admin/size")
public class SizeController {

    @Autowired
    private ISizeService sizeService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<SizeDTO> pageSizes;
        List<SizeDTO> sizes;
        sortable = PagingUtils.sortByCreatedDate(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pageSizes = sizeService.findAllPageable(status, pageable);
            sizes = pageSizes.getContent();
            response.put("currentPage", pageSizes.getNumber() + 1);
            response.put("totalItems", pageSizes.getTotalElements());
            response.put("totalPages", pageSizes.getTotalPages());
        } else {
            sizes = sizeService.findAll(status, sortable);
        }
        response.put("sizes", sizes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public SizeDTO create(@RequestBody SizeRequest dto){
        dto.setStatus(SystemConstant.ACTIVE_STATUS);
        return sizeService.save(dto);
    }

    @PutMapping
    public SizeDTO update(@RequestBody SizeRequest dto){
        return sizeService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return sizeService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public SizeDTO detail(@PathVariable("id") Long id){
        return sizeService.findById(id);
    }

}
