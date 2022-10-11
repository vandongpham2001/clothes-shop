package com.example.clothesshop.controller.admin;

import com.example.clothesshop.dto.ProductToPromotion;
import com.example.clothesshop.dto.PromotionDTO;
import com.example.clothesshop.service.IPromotionService;
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

@RestController(value = "apiPromotionOfAdmin")
@RequestMapping(path = "api/admin/promotion")
public class PromotionController {
    @Autowired
    private IPromotionService promotionService;
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(value = "page", required = false) Integer page,
                                                      @RequestParam(value = "limit", required = false) Integer limit,
                                                      @RequestParam(value = "sort", required = false, defaultValue = "asc") String sort,
                                                      @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable;
        Sort sortable;
        Page<PromotionDTO> pagePromotions;
        List<PromotionDTO> promotions;
        sortable = PagingUtils.sort(sort);
        if (page != null && limit != null) {
            pageable = PageRequest.of(page - 1, limit, sortable);
            pagePromotions = promotionService.findAllPageable(status, pageable);
            promotions = pagePromotions.getContent();
            response.put("currentPage", pagePromotions.getNumber() + 1);
            response.put("totalItems", pagePromotions.getTotalElements());
            response.put("totalPages", pagePromotions.getTotalPages());
        } else {
            promotions = promotionService.findAll(status, sortable);
        }
        response.put("promotions", promotions);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public PromotionDTO create(@ModelAttribute PromotionDTO dto){
        return promotionService.save(dto);
    }

    @PutMapping
    public PromotionDTO update(@ModelAttribute PromotionDTO dto){
        return promotionService.save(dto);
    }

    @DeleteMapping
    public String delete(@RequestBody long[] ids) {
        return promotionService.delete(ids);
    }

    @GetMapping(value = "/{id}")
    public PromotionDTO detail(@PathVariable("id") Long id){
        return promotionService.findById(id);
    }

    @PostMapping(value = "/product")
    public PromotionDTO addProductToPromotion(@RequestBody ProductToPromotion form){
        return promotionService.addProductToPromotion(form.getPromotion_id(), form.getProduct_id());
    }
    @DeleteMapping(value = "/product")
    public PromotionDTO removeProductFromPromotion(@RequestBody ProductToPromotion form){
        return promotionService.removeProductFromPromotion(form.getPromotion_id(), form.getProduct_id());
    }
}
